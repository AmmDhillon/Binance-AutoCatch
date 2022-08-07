package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.Constants;
import com.ammdhillon.autocatch.component.RestTemplateFactory;
import com.ammdhillon.autocatch.helper.Utils;
import com.ammdhillon.autocatch.model.entity_model.swap_transaction.SwapTransaction;
import com.ammdhillon.autocatch.model.entity_model.token.Token;
import com.ammdhillon.autocatch.model.enums.ContractCurrency;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.pojo.AccountBalance;
import com.ammdhillon.autocatch.model.pojo.BalanceDetail;
import com.ammdhillon.autocatch.model.pojo.ContractDetail;
import com.ammdhillon.autocatch.model.pojo.crypto_info.CryptoInfo;
import com.ammdhillon.autocatch.model.pojo.crypto_info.DataItem;
import com.ammdhillon.autocatch.model.pojo.crypto_info.Platform;
import com.ammdhillon.autocatch.model.pojo.transaction_status.TransactionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.ammdhillon.autocatch.Constants.*;

@Service
public class CryptoAPI {

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    public ContractDetail getContractAddress(Token token) {
        Map<String, String> contracts = getContracts(token);

        if (contracts == null) return null;

        String contractBSC = contracts.get(Constants.BSC_SCAN);
        String contractETH = contracts.get(Constants.ETHER_SCAN);
        String contractPOLY = contracts.get(Constants.POLY_SCAN);

        if (Utils.isValid(contractBSC)) {
            return new ContractDetail(contractBSC, ContractType.BSC, ContractCurrency.BNB);
        } else if (Utils.isValid(contractPOLY)) {
            return new ContractDetail(contractPOLY, ContractType.POLYGON, ContractCurrency.MATIC);
        } else if (Utils.isValid(contractETH)) {
            return new ContractDetail(contractETH, ContractType.ETH, ContractCurrency.ETH);
        } else {
            return null;
        }
    }

    private Map<String, String> getContracts(Token token) {
        try {
            String URI = Constants.ANNOUNCEMENT_TOKEN_URL + token.getBinanceCode();

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            System.out.println("URI - " + URI);

            ResponseEntity<String> response = restTemplate.getForEntity(URI, String.class);

            if (response.getBody() == null) return null;

            String body = response.getBody();

            System.out.println("Response - " + body);
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            String[] arr = body.split("\"");

            Map<String, String> contracts = new HashMap<>();

            String contractETH = null;
            String contractBSC = null;
            String contractPOLY = null;

            for (String str : arr) {
                if (str.contains(Constants.POLY_SCAN)) {
                    System.out.println(str);
                    String[] url = str.split("/");
                    contractPOLY = url[url.length - 1].replaceAll("[^a-zA-Z0-9]", "");
                    System.out.println("POLYGON CONTRACT: " + contractPOLY);
                    break;
                }

                if (str.contains(Constants.BSC_SCAN)) {
                    System.out.println(str);
                    String[] url = str.split("/");
                    contractBSC = url[url.length - 1].replaceAll("[^a-zA-Z0-9]", "");
                    System.out.println("BSC CONTRACT: " + contractBSC);
                    break;
                }

                if (str.contains(Constants.ETHER_SCAN)) {
                    String[] url = str.split("/");
                    contractETH = url[url.length - 1].replaceAll("[^a-zA-Z0-9]", "");
                    System.out.println("ETH CONTRACT: " + contractETH);
                }
            }

            contracts.put(Constants.ETHER_SCAN, contractETH);
            contracts.put(Constants.BSC_SCAN, contractBSC);
            contracts.put(Constants.POLY_SCAN, contractPOLY);

            return contracts;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Some coin symbols were null, so didn't use CoinMarketCap API for fetching Symbols
    private String getContractFromCMC(Token token) {
        try {
            String URI = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/map";

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            request.set("X-CMC_PRO_API_KEY", "dsfsdfdsf-fa03-4abe-8b7e-fdgfdgdfg"); // Dummy API Key

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI)
                    .queryParam(Constants.SYMBOL, token.getSymbol());

            HttpEntity<String> entity = new HttpEntity<>(request);

            System.out.println("URI - " + builder.toUriString());

            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            if (response.getBody() == null) return null;

            System.out.println("Response - " + response.getBody());
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            CryptoInfo cryptoInfo = objectMapper.readValue(response.getBody(), CryptoInfo.class);

            String contractAddress = null;

            for (DataItem item : cryptoInfo.getData()) {
                if (item.getName().toLowerCase().contains(token.getName().toLowerCase())) {
                    Platform platform = item.getPlatform();

                    if (platform != null && platform.getSymbol().equals("ETH")) {
                        contractAddress = platform.getTokenAddress();
                        break;
                    }
                }
            }

            return contractAddress;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public TransactionStatus checkTransactionStatus(SwapTransaction transaction) {
        try {
            String hash = transaction.getHash();

            String activeURL = ETH_URI;
            String activeKEY = ETH_KEY;

            switch (transaction.getContractType()) {
                case BSC:
                    activeURL = BSC_URI;
                    activeKEY = BSC_KEY;
                    break;
                case ETH:
                    activeURL = ETH_URI;
                    activeKEY = ETH_KEY;
                    break;
                case POLYGON:
                    activeURL = POLY_URI;
                    activeKEY = POLY_KEY;
                    break;
            }

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(activeURL)
                    .queryParam(Constants.MODULE, "transaction")
//                .queryParam(Constants.ACTION, "gettxreceiptstatus") // Returns the status code of a transaction execution
                    .queryParam(Constants.ACTION, "getstatus") // Returns the status code of a contract execution
                    .queryParam(Constants.TX_HASH, hash)
                    .queryParam(Constants.API_KEY, activeKEY);

            HttpEntity<String> entity = new HttpEntity<>(request);

            System.out.println("URI - " + builder.toUriString());

            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            if (response.getBody() == null) return null;

            System.out.println("Response - " + response.getBody());
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            TransactionStatus status = objectMapper.readValue(response.getBody(), TransactionStatus.class);
            return status;
//            return status.getResult().getStatus().equals("1"); // Returns the status code of a transaction execution
//            return status.getResult().getIsError().equals("0"); // Returns the status code of a contract execution
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public BalanceDetail getBalance(ContractType contractType, String address) {
        try {
            String activeURL = ETH_URI;
            String activeKEY = ETH_KEY;

            switch (contractType) {
                case BSC:
                    activeURL = BSC_URI;
                    activeKEY = BSC_KEY;
                    break;
                case ETH:
                    activeURL = ETH_URI;
                    activeKEY = ETH_KEY;
                    break;
                case POLYGON:
                    activeURL = POLY_URI;
                    activeKEY = POLY_KEY;
                    break;
            }

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(activeURL)
                    .queryParam(Constants.MODULE, "account")
                    .queryParam(Constants.ACTION, "balance") // Returns the status code of a contract execution
                    .queryParam(Constants.ADDRESS, address)
                    .queryParam(Constants.TAG, "latest")
                    .queryParam(Constants.API_KEY, activeKEY);

            HttpEntity<String> entity = new HttpEntity<>(request);

            System.out.println("URI - " + builder.toUriString());

            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            if (response.getBody() == null) return null;

            System.out.println("Response - " + response.getBody());
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            String balance = objectMapper.readValue(response.getBody(), AccountBalance.class).getResult();

            BalanceDetail balanceDetail = new BalanceDetail();

            balanceDetail.setContractType(contractType);
            balanceDetail.setAmount(Utils.fromWei(balance));

            return balanceDetail;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AccountBalance getTokenBalance(String myAddress, Token token) {
        try {
            String contract = token.getContract();

            String activeURL = ETH_URI;
            String activeKEY = ETH_KEY;

            switch (token.getContractType()) {
                case BSC:
                    activeURL = BSC_URI;
                    activeKEY = BSC_KEY;
                    break;
                case ETH:
                    activeURL = ETH_URI;
                    activeKEY = ETH_KEY;
                    break;
                case POLYGON:
                    activeURL = POLY_URI;
                    activeKEY = POLY_KEY;
                    break;
            }

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(activeURL)
                    .queryParam(Constants.MODULE, "account")
                    .queryParam(Constants.ACTION, "tokenbalance") // Returns the status code of a contract execution
                    .queryParam(Constants.CONTRACT_ADDRESS, contract)
                    .queryParam(Constants.ADDRESS, myAddress)
                    .queryParam(Constants.TAG, "latest")
                    .queryParam(Constants.API_KEY, activeKEY);

            HttpEntity<String> entity = new HttpEntity<>(request);

            System.out.println("URI - " + builder.toUriString());

            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            if (response.getBody() == null) return null;

            System.out.println("Response - " + response.getBody());
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getBody(), AccountBalance.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}