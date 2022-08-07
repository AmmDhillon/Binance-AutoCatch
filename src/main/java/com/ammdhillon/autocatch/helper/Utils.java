package com.ammdhillon.autocatch.helper;

import com.ammdhillon.autocatch.Constants;
import com.ammdhillon.autocatch.model.entity_model.data.Data;
import com.ammdhillon.autocatch.model.entity_model.token.Token;
import com.ammdhillon.autocatch.model.entity_model.wrapper.ErrorResponse;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.enums.Key;
import com.ammdhillon.autocatch.model.pojo.BinanceToken;
import com.ammdhillon.autocatch.model.pojo.ContractDetail;
import com.ammdhillon.autocatch.model.pojo.SwapRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.web3j.utils.Convert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static DecimalFormat decimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat;
    }

    public static boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isValid(Long lo) {
        return lo != null;
    }

    public static String toWei(Double value) {
        return Convert.toWei(String.valueOf(value), Convert.Unit.ETHER).toBigIntegerExact().toString();
    }

    public static Double getTargetAmount(Double amount, Double target) {
        // target = 0.50, means 50%
        return Double.valueOf(decimalFormat().format(amount + (amount * target)));
    }

    public static Double fromWei(BigInteger value) {
        return Double.valueOf(decimalFormat().format(value.doubleValue() / Constants.WEI));
    }

    public static Double fromWei(String value) {
        return Double.valueOf(decimalFormat().format(Double.parseDouble(value) / Constants.WEI));
    }

    public static SwapRequest getSellSwapRequest(Token token, Data data) {
        SwapRequest swapRequest = new SwapRequest();

        swapRequest.setBuyToken(token.getContractCurrency().name());
        swapRequest.setSellToken(token.getContract());
        swapRequest.setSellAmount(Utils.toWei(token.getAmount()));
        swapRequest.setContractType(token.getContractType());

        if (token.getContractType() == ContractType.BSC) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountBNB()));
        } else if (token.getContractType() == ContractType.ETH) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountETH()));
        } else if (token.getContractType() == ContractType.POLYGON) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountMATIC()));
        }

        // Sell Slippage - When SELLING a previously swapped Token
        swapRequest.setSlippagePercentage(data.getSellSlippage());

        return swapRequest;
    }

    public static SwapRequest getBuySwapRequest(ContractDetail contractDetail, Data data) {
        SwapRequest swapRequest = new SwapRequest();

        swapRequest.setBuyToken(contractDetail.getContract());
        swapRequest.setSellToken(contractDetail.getCurrency().name());
        swapRequest.setContractType(contractDetail.getType());

        if (contractDetail.getType() == ContractType.BSC) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountBNB()));
        } else if (contractDetail.getType() == ContractType.ETH) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountETH()));
        } else if (contractDetail.getType() == ContractType.POLYGON) {
            swapRequest.setSellAmount(Utils.toWei(data.getBuyAmountMATIC()));
        }

        // Buy Slippage - When BUYING a new Token using ETH
        swapRequest.setSlippagePercentage(data.getBuySlippage());

        return swapRequest;
    }

    public static Token getToken(BinanceToken binanceToken) {
        String title = binanceToken.getTitle();

        Token token = null;

//        if (title.contains("AUTO_CATCH")) {
//            title = title.replace("AUTO_CATCH", "").trim();
//        }

        if (title.startsWith("Binance Will List")) {
            String stripped;

            if (title.contains("https")) {
                stripped = title.replace("Binance Will List ", "").split("\n")[0];
                System.out.println(stripped);
            } else {
                stripped = title.replace("Binance Will List ", "");
            }

            if (stripped.contains(" and ")) {
                String[] strippedTokens = stripped.split(" and ");

                for (String tokens : strippedTokens) {
                    String[] tokenDetail = tokens.split(" \\(");
                    String tokenName = tokenDetail[0].trim();
                    String tokenSymbol = tokenDetail[1].split("\\)")[0];

                    token = new Token();

                    token.setName(tokenName);
                    token.setSymbol(tokenSymbol);
                    token.setReleaseData(binanceToken.getReleaseDate());
                    token.setBinanceCode(binanceToken.getCode());
                    token.setBinanceId(binanceToken.getId());
                }
            } else {
                String[] tokenDetail = stripped.split(" \\(");
                String tokenName = tokenDetail[0].trim();
                String tokenSymbol = tokenDetail[1].split("\\)")[0];

                token = new Token();

                token.setName(tokenName);
                token.setSymbol(tokenSymbol);
                token.setReleaseData(binanceToken.getReleaseDate());
                token.setBinanceCode(binanceToken.getCode());
                token.setBinanceId(binanceToken.getId());
            }
        }

        return token;
    }

    public static boolean isNumberValid(String number) {
        try {
            return isNumberValid(Long.valueOf(number));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumberValid(Long number) {
        return number > 999999999;
    }

    public static void logRequest(HttpServletRequest request) {
        try {
            System.out.println(String.format(Constants.REQUEST_RECEIVED, getCurrentTime(),
                    request.getMethod(), request.getServletPath(), CharStreams.toString(request.getReader())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getCurrentTime() {
        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(date);
    }

    public static Double userCommission(Double amount, Double percentage, Boolean isFlat) {
        if (isFlat) return percentage;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format((amount * percentage) / 100));
    }

    public static Double adminCommission(Double amount, Double percentage, Boolean isFlat) {
        if (isFlat) return percentage;
        return (amount * percentage) / 100;
    }

    public static String getKey(Key key) {
        return key.name().toLowerCase();
    }

    public static Long getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.valueOf(String.valueOf(auth.getPrincipal()));
    }

    public static void raiseError(HttpServletResponse response, HttpStatus httpStatus, boolean logout, String msg) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), logout, msg);
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}