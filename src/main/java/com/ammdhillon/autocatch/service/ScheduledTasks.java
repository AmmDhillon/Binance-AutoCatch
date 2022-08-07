package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.Constants;
import com.ammdhillon.autocatch.component.RestTemplateFactory;
import com.ammdhillon.autocatch.helper.Utils;
import com.ammdhillon.autocatch.model.entity_model.swap_transaction.SwapTransaction;
import com.ammdhillon.autocatch.model.entity_model.token.Token;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.enums.FCMTopic;
import com.ammdhillon.autocatch.model.enums.Status;
import com.ammdhillon.autocatch.model.pojo.AccountBalance;
import com.ammdhillon.autocatch.model.pojo.BinanceToken;
import com.ammdhillon.autocatch.model.pojo.ContractDetail;
import com.ammdhillon.autocatch.model.pojo.PushNotificationRequest;
import com.ammdhillon.autocatch.model.pojo.swap_quote.SwapQuote;
import com.ammdhillon.autocatch.model.pojo.transaction_status.TransactionStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

@Service
public class ScheduledTasks {

    @Autowired
    private OxAPI oxAPI;

    @Autowired
    private CryptoAPI cryptoAPI;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    @Autowired
    private SigningService signingService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DataService dataService;

    @Autowired
    private SwapTransactionService swapTransactionService;

    @Autowired
    private FCMService fcmService;

    private Gson gson;

    // 3rd Party Proxies (Use Proxies, In case Binance decided to ban your IP due to excessive scraping calls)
    // But it's rare that Binance will ban your IP
    // But just in case
    private String[] proxyCurl = new String[] {"/usr/bin/proxychains", "/usr/bin/curl", Constants.ANNOUNCEMENT_URL};

    // Server IP
    private String[] normalCurl = new String[] {"/usr/bin/curl", Constants.ANNOUNCEMENT_URL};

    private String[] activeCurl;

    private int count = 0;

    private static final int COUNT_HIT = 4; // Scheduler running every 15 seconds, 15 * 4 = 60 seconds

    public ScheduledTasks() {
        activeCurl = normalCurl; // Switch between server IP and Proxies
        gson = new Gson().newBuilder().setLenient().create();
    }

    private void updateBalances() {
        for (Token token : tokenService.getSwapped()) {
            AccountBalance accountBalance = cryptoAPI.getTokenBalance(dataService.getMyAddress(), token);

            token.setAmount(Utils.fromWei(accountBalance.getResult()));

            tokenService.saveToken(token);
        }
    }

    @Scheduled(fixedRate = 15 * 1000) // Every 15 second
    public void miscDataGatheringJob() {
        try {
            if (!dataService.isActive()) {
                System.out.println("MISC JOB HALTED!!!");
                return;
            } else {
                count++;
                System.out.println("MISC JOB WORKING");
            }

            for (SwapTransaction transaction : swapTransactionService.getPending()) {
                TransactionStatus status = cryptoAPI.checkTransactionStatus(transaction);

                if (status != null) {
                    PushNotificationRequest request = new PushNotificationRequest();
                    request.setTopic(FCMTopic.TOKEN_SWAP_STATUS.name());

                    if (status.getResult().getIsError().equals("0")) {
                        transaction.setStatus(Status.SUCCESS);

                        request.setTitle("AutoSwap SUCCESSFUL!!!");
                        request.setMessage(String.format("Transaction Hash: %s", transaction.getHash()));
                    } else {
                        transaction.setStatus(Status.FAILED);
                        transaction.setRemark(status.getResult().getErrDescription());

                        request.setTitle("AutoSwap Failed");
                        request.setMessage(String.format("Remark %s", transaction.getRemark()));
                    }

                    fcmService.sendMessageWithoutData(request);
                } else {
                    transaction.setStatus(Status.PENDING);
                }

                swapTransactionService.saveTransaction(transaction);

                if (transaction.getStatus() == Status.SUCCESS) {
                    updateBalances();
                }
            }

            if (dataService.getAutoSell() && count >= COUNT_HIT) {
                System.out.println("AUTO SELL WORKING!!!");

                count = 0;

                for (Token token : tokenService.getAutoSell()) {
                    updateBalances();

                    System.out.println("Token Name: " + token.getName());
                    System.out.println("Token Symbol: " + token.getSymbol());
                    System.out.println("Token Contract: " + token.getContract());
                    System.out.println("Token Amount: " + token.getAmount());
                    System.out.println("Token Amount in WEI: " + Utils.toWei(token.getAmount()));
                    System.out.println("Token Type: " + token.getContractType().name());
                    System.out.println("Token Currency: " + token.getContractCurrency().name());
                    System.out.println("Token Sold: " + token.getSold());
                    System.out.println("Token Target: " + token.getTarget());

                    SwapQuote swapQuote = oxAPI.swapTokens(Utils.getSellSwapRequest(token, dataService.getData()));
                    Double buyAmount = Utils.fromWei(swapQuote.getBuyAmount());
                    Double target = token.getTarget();

                    System.out.println("Quote Amount: " + buyAmount);
                    System.out.println("Target Amount: " + target);

                    if (buyAmount >= target) {
                        System.out.println("---------------------------------------");
                        System.out.println("!!! IT'S NOT A HIT, IT'S A HOME RUN !!!");
                        System.out.println("---------------------------------------");

                        PushNotificationRequest request = new PushNotificationRequest();

                        request.setTitle("Congratulations - Token Sell Target Hit!");
                        request.setTopic(FCMTopic.AUTO_SELL_TOPIC.name());

                        if (token.getAutoSell()) {
                            request.setMessage("AutoSell is enabled for this token. It should be sold automatically.");
                        } else {
                            request.setMessage("AutoSell is disabled for this token. Please sell it manually.");
                        }

                        fcmService.sendMessageWithoutData(request);

                        token.setAutoSell(false);
                        tokenService.saveToken(token);
                    } else {
                        System.out.println("TARGET NOT HIT YET");
                    }

                    System.out.println("Difference: " + (target - buyAmount));
                }
            } else {
                System.out.println("AUTO SELL NOT WORKING");
            }
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in getting Pending Transactions");
        }
    }

    @Scheduled(fixedRate = 10 * 1000) // number of minutes * milli seconds in a second
    public void autoFetchAnnouncementJob() {
        try {
            if (!dataService.isSchedulerActive()) {
                System.out.println("SCHEDULER HALTED");
                return;
            } else {
                System.out.println("SCHEDULER WORKING!!!");
            }

            ProcessBuilder pb = new ProcessBuilder(activeCurl);
            Process process = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder out = new StringBuilder();

            String line, previous = null;

            while ((line = br.readLine()) != null) {
                if (!line.equals(previous)) {
                    previous = line;
                    out.append(line).append('\n');
                }
            }

            // Check result
            if (process.waitFor() == 0) {
                process.destroy();
                formatData(out.toString());
            }

            activeCurl = proxyCurl;
            proxyCurl[proxyCurl.length - 1] = dataService.getAnnouncementUrl();
            normalCurl[normalCurl.length - 1] = dataService.getAnnouncementUrl();
            System.out.println("Active cURL is Proxy now");
        } catch (Exception e) {
            e.printStackTrace();
            activeCurl = normalCurl;
            System.out.println("Active cURL is Normal now");
        }
    }

    private void formatData(String data) {
        String[] arr = data.split("[\\{\\]]");

        HashSet<BinanceToken> _list = new HashSet<>();

        for (String str : arr) {
            if (str.contains("Binance Will List")) {
                String binanceItem = String.format("{%s", str.replaceAll(",$", ""));
                BinanceToken binanceToken = gson.fromJson(binanceItem, BinanceToken.class);
                System.out.print("| " + binanceToken.getTitle() + " |");
                _list.add(binanceToken);
            }
        }

        System.out.println("Binance Fetch SUCCESSFUL!!!");
        System.out.println("Tokens Fetched: " + _list.size());

        if (_list.isEmpty()) return;

        for (BinanceToken binanceToken : _list) {
            Token token = Utils.getToken(binanceToken);

            if (!tokenService.isTokenExists(token)) {
                System.out.println("NEW TOKEN ALERT: " + binanceToken.getTitle());

                PushNotificationRequest request = new PushNotificationRequest();

                request.setTitle("NEW TOKEN ALERT!!!");
                request.setTopic(FCMTopic.NEW_TOKEN_ALERT_TOPIC.name());
                request.setMessage(String.format("%s (%s) has been listed", token.getName(), token.getSymbol()));

                fcmService.sendMessageWithoutData(request);

                automatedSwap(token);
            }
        }
    }

    public void automatedSwap(Token token) {
        // Step 1 - Check If Token Already Exists. Halt process, if true
        System.out.println("Step 1 - Check If Token Already Exists. Halt process, if true");
        if (token == null) return;
        if (tokenService.isTokenExists(token)) return;

        Long startTime = System.currentTimeMillis();

        token = tokenService.saveToken(token);

        if (!dataService.isActive()) {
            System.out.println("SWAPPING HALTED");
            return;
        }

        System.out.println("Automated Swap Initiated!!!");

        try {
            // Step - 2 - Get Contract Address from CoinMarketCap
            System.out.println("Step - 2 - Get Contract Address from either CoinMarketCap || EtherScan");
            ContractDetail contractDetail = cryptoAPI.getContractAddress(token);

            if (contractDetail != null) {
                System.out.println("CONTRACT_ADDRESS_TO_USE: " + contractDetail.getContract());

                // Step - 3 - Create Swap Request
                System.out.println("Step - 3 - Create Swap Request");
                SwapQuote swapQuote = oxAPI.swapTokens(Utils.getBuySwapRequest(contractDetail, dataService.getData()));

                // Step - 4 - Sign Transaction
                System.out.println("Step - 4 - Sign Transaction");
                SwapTransaction transaction = signingService.signTransaction(contractDetail, swapQuote, dataService.getData());

                swapTransactionService.saveTransaction(transaction);

                // Update contract address in above saved token
                token.setContract(contractDetail.getContract());
                token.setSwapped(true);
                token.setContractType(contractDetail.getType());
                token.setContractCurrency(contractDetail.getCurrency());
                token.setAutoSell(dataService.getAutoSell());

                if (contractDetail.getType() == ContractType.BSC) {
                    // dataService.getSellTarget() = 0.50, means 50%
                    token.setTarget(Utils.getTargetAmount(dataService.getBuyAmountBNB(), dataService.getSellTarget()));
                    // Amount of Currency used for swap
                    token.setSold(dataService.getBuyAmountBNB());
                } else if (contractDetail.getType() == ContractType.ETH) {
                    token.setTarget(Utils.getTargetAmount(dataService.getBuyAmountETH(), dataService.getSellTarget()));
                    token.setSold(dataService.getBuyAmountETH());
                }

                tokenService.saveToken(token);

                PushNotificationRequest request = new PushNotificationRequest();

                request.setTopic(FCMTopic.TOKEN_SWAP_STATUS.name());

                if (transaction.getStatus().equals(Status.PENDING)) {
                    request.setTitle(String.format("SwapRequest submitted successfully for %s (%s)", token.getName(), token.getSymbol()));
                    request.setMessage("Swap transaction is under process now");

                    System.out.println("Step - 5 - Sign Transaction");

                    System.out.println("HURRAY!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("HURRAY!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("HURRAY!!!!!!!!!!!!!!!!!!!!!!");

                    System.out.println("------------------ TRANSACTION DONE | STATUS PENDING ------------------");
                } else {
                    request.setTitle(String.format("SwapRequest submission failed for %s (%s)", token.getName(), token.getSymbol()));
                    request.setMessage(String.format("Remark: %s", transaction.getRemark()));

                    System.out.println(String.format("Transaction failed. Remark: %s", transaction.getRemark()));
                }

                fcmService.sendMessageWithoutData(request);
            } else {
                System.out.println("SOMETHING WENT WRONG :(");
                System.out.println("PROBABLY DIDN'T GET THE CONTRACT ADDRESS FROM CMC :(");
            }

            Long endTime = System.currentTimeMillis();

            long timeTaken = endTime - startTime;

            System.out.println("Total Time Taken for the Process: " + timeTaken);
        } catch (Exception e) {
            tokenService.removeToken(token);
            System.out.println("Token removed due to failure, now retrying...");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}