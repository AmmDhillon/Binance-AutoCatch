package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.helper.Utils;
import com.ammdhillon.autocatch.model.entity_model.data.Data;
import com.ammdhillon.autocatch.model.entity_model.swap_transaction.SwapTransaction;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.enums.Status;
import com.ammdhillon.autocatch.model.pojo.ContractDetail;
import com.ammdhillon.autocatch.model.pojo.swap_quote.SwapQuote;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

@Service
public class SigningService {

    public SwapTransaction signTransaction(ContractDetail contractDetail, SwapQuote quote, Data data) throws Exception {
        SwapTransaction swapTransaction = new SwapTransaction();

        swapTransaction.setTest(data.getIsTesting());

        BigInteger gasPrice = BigInteger.valueOf(Long.parseLong(quote.getGasPrice()));
        BigInteger gasLimit = BigInteger.valueOf(Long.parseLong(quote.getGas()) * 2);
        String to = quote.getTo();
        BigInteger value = BigInteger.valueOf(Long.parseLong(quote.getValue()));
        String quoteData = quote.getData();

        swapTransaction.setAmount(Utils.fromWei(value));
        swapTransaction.setToAddress(to);

        String nodeURL;
        long chainId = 0;

        if (contractDetail.getType() == ContractType.BSC) {
            String[] arr = data.getIsTesting() ? data.getTestNodeBSC().split("::") : data.getMainNodeBSC().split("::");
            nodeURL = arr[0];
            chainId = Long.parseLong(arr[1]);
        } else if (contractDetail.getType() == ContractType.POLYGON) {
            String[] arr = data.getIsTesting() ? data.getTestNodePOLYGON().split("::") : data.getMainNodePOLYGON().split("::");
            nodeURL = arr[0];
            chainId = Long.parseLong(arr[1]);
        } else if (contractDetail.getType() == ContractType.ETH) {
            nodeURL = data.getIsTesting() ? data.getTestNodeETH() : data.getMainNodeETH();
        } else {
            System.out.println("Node URL Null");

            swapTransaction.setStatus(Status.FAILED);
            swapTransaction.setRemark("Node URL Null");
            return swapTransaction;
        }

        System.out.println("NODE URL: " + nodeURL);

        Web3j web3 = Web3j.build(new HttpService(nodeURL));

        EthBlockNumber blockNumber = web3.ethBlockNumber().send();

        BigInteger nonce = web3.ethGetTransactionCount(data.getMyAddress(),
                DefaultBlockParameter.valueOf(blockNumber.getBlockNumber())).send().getTransactionCount();

        RawTransaction transaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, quoteData);

        byte[] signedMessage = null;

        if (contractDetail.getType() == ContractType.BSC) {
            signedMessage = TransactionEncoder.signMessage(transaction, chainId, Credentials.create(data.getJeremy()));
        } else if (contractDetail.getType() == ContractType.ETH) {
            signedMessage = TransactionEncoder.signMessage(transaction, Credentials.create(data.getJeremy()));
        } else if (contractDetail.getType() == ContractType.POLYGON) {
            signedMessage = TransactionEncoder.signMessage(transaction, chainId, Credentials.create(data.getJeremy()));
        }

        if (signedMessage == null) {
            System.out.println("Signed message is null");

            swapTransaction.setStatus(Status.FAILED);
            swapTransaction.setRemark("Signed message is null");
            return swapTransaction;
        }

        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction sendTransaction = web3.ethSendRawTransaction(hexValue).send();

        String hash = sendTransaction.getTransactionHash();

        swapTransaction.setHash(hash);

        if (!Utils.isValid(hash)) {
            String error = sendTransaction.getError().getMessage();
            System.out.println(error);
            swapTransaction.setStatus(Status.FAILED);
            swapTransaction.setRemark(error);
        } else {
            swapTransaction.setStatus(Status.PENDING);
        }

        swapTransaction.setContract(contractDetail.getContract());
        swapTransaction.setFromAddress(data.getMyAddress());
        swapTransaction.setContractType(contractDetail.getType());
        swapTransaction.setContractCurrency(contractDetail.getCurrency());

        return swapTransaction;
    }
}