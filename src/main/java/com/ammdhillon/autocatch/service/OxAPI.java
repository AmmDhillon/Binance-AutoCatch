package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.Constants;
import com.ammdhillon.autocatch.component.RestTemplateFactory;
import com.ammdhillon.autocatch.model.pojo.SwapRequest;
import com.ammdhillon.autocatch.model.pojo.swap_quote.SwapQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.ammdhillon.autocatch.Constants.*;

@Service
public class OxAPI {

    @Autowired
    private RestTemplateFactory restTemplateFactory;



    public SwapQuote swapTokens(SwapRequest swapRequest) {
        try {
            String activeURL = ETH_URI_0X;

            switch (swapRequest.getContractType()) {
                case BSC:
                    activeURL = BSC_URI_0X;
                    break;
                case ETH:
                    activeURL = ETH_URI_0X;
                    break;
                case POLYGON:
                    activeURL = POLY_URI_0X;
                    break;
            }

            RestTemplate restTemplate = restTemplateFactory.getObject();

            if (restTemplate == null) return null;

            HttpHeaders request = new HttpHeaders();

            request.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(activeURL)
                    .queryParam(Constants.BUY_TOKEN, swapRequest.getBuyToken())
                    .queryParam(Constants.SELL_TOKEN, swapRequest.getSellToken())
                    .queryParam(Constants.SLIPPAGE_PERCENTAGE, swapRequest.getSlippagePercentage())
                    .queryParam(swapRequest.getSellAmount() == null ? Constants.BUY_AMOUNT : Constants.SELL_AMOUNT,
                            swapRequest.getSellAmount() == null ? swapRequest.getBuyAmount() : swapRequest.getSellAmount());

            HttpEntity<String> entity = new HttpEntity<>(request);

            System.out.println("URI - " + builder.toUriString());

            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            if (response.getBody() == null) return null;

            System.out.println("Response - " + response.getBody());
            System.out.println("Headers - " + response.getHeaders());
            System.out.println("Status - " + response.getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getBody(), SwapQuote.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
