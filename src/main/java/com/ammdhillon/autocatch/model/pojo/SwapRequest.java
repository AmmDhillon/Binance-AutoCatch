package com.ammdhillon.autocatch.model.pojo;

import com.ammdhillon.autocatch.model.enums.ContractType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapRequest {

    private String buyToken;
    private String sellToken;
    private String buyAmount;
    private Double slippagePercentage;
    private String sellAmount;
    private ContractType contractType;
    private Boolean isTest;
}