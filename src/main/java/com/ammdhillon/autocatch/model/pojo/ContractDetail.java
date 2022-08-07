package com.ammdhillon.autocatch.model.pojo;

import com.ammdhillon.autocatch.model.enums.ContractCurrency;
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
public class ContractDetail {

	private String contract;
	private ContractType type;
	private ContractCurrency currency;
}