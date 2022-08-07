package com.ammdhillon.autocatch.api;

import com.ammdhillon.autocatch.Constants;
import com.ammdhillon.autocatch.ResponseInterface;
import com.ammdhillon.autocatch.helper.Routes;
import com.ammdhillon.autocatch.model.entity_model.data.Data;
import com.ammdhillon.autocatch.model.entity_model.token.Token;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.enums.FCMTopic;
import com.ammdhillon.autocatch.model.pojo.BalanceDetail;
import com.ammdhillon.autocatch.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping(Routes.DATA)
@RestController
public class DataController implements ResponseInterface {

    @Autowired
    private DataService dataService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SwapTransactionService swapTransactionService;

    @Autowired
    private CryptoAPI cryptoAPI;

    @PostMapping(Routes.UPDATE_DATA)
    public ResponseEntity<Object> updateData(@Valid @RequestBody MultiValueMap<String, String> body) {
        ObjectMapper objectMapper = new ObjectMapper();
        Data data = objectMapper.convertValue(body.toSingleValueMap(), Data.class);

        dataService.saveData(data);
        return respondMsgSuccess(Constants.OPERATION_SUCCESSFUL);
    }

    @GetMapping(Routes.GET_DATA)
    public ResponseEntity<Object> getData() {
        return respondObject(dataService.getData());
    }

    @GetMapping(Routes.GET_TOPICS)
    public ResponseEntity<Object> getTopics() {
        return respondObject(FCMTopic.values());
    }

    @GetMapping(Routes.GET_TRANSACTIONS)
    public ResponseEntity<Object> getTransactions() {
        return respondObject(swapTransactionService.getAll());
    }

    @GetMapping(Routes.GET_TOKENS)
    public ResponseEntity<Object> getTokens() {
        return respondObject(tokenService.getAll());
    }

    @GetMapping(Routes.GET_BALANCES)
    public ResponseEntity<Object> getBalances() {
        String myAddress = dataService.getMyAddress();

        ArrayList<BalanceDetail> _balances = new ArrayList<>();

        _balances.add(cryptoAPI.getBalance(ContractType.ETH, myAddress));
        _balances.add(cryptoAPI.getBalance(ContractType.BSC, myAddress));
        _balances.add(cryptoAPI.getBalance(ContractType.POLYGON, myAddress));

        return respondObject(_balances);
    }

    @PostMapping(Routes.UPDATE_TOKEN)
    public ResponseEntity<Object> updateToken(@Valid @RequestBody MultiValueMap<String, String> body) {
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.convertValue(body.toSingleValueMap(), Token.class);
        tokenService.saveToken(token);
        return respondMsgSuccess(Constants.OPERATION_SUCCESSFUL);
    }
}