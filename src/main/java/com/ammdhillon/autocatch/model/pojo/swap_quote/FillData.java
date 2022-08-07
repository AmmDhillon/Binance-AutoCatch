package com.ammdhillon.autocatch.model.pojo.swap_quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FillData{

	@JsonProperty("router")
	private String router;

	@JsonProperty("uniswapPath")
	private String uniswapPath;

	@JsonProperty("tokenAddressPath")
	private List<String> tokenAddressPath;
}