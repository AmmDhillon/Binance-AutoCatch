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
public class SwapQuote{

	@JsonProperty("buyTokenAddress")
	private String buyTokenAddress;

	@JsonProperty("guaranteedPrice")
	private String guaranteedPrice;

	@JsonProperty("data")
	private String data;

	@JsonProperty("sources")
	private List<SourcesItem> sources;

	@JsonProperty("protocolFee")
	private String protocolFee;

	@JsonProperty("sellTokenToEthRate")
	private String sellTokenToEthRate;

	@JsonProperty("estimatedGas")
	private String estimatedGas;

	@JsonProperty("sellTokenAddress")
	private String sellTokenAddress;

	@JsonProperty("allowanceTarget")
	private String allowanceTarget;

	@JsonProperty("buyAmount")
	private String buyAmount;

	@JsonProperty("chainId")
	private int chainId;

	@JsonProperty("price")
	private String price;

	@JsonProperty("sellAmount")
	private String sellAmount;

	@JsonProperty("buyTokenToEthRate")
	private String buyTokenToEthRate;

	@JsonProperty("gas")
	private String gas;

	@JsonProperty("orders")
	private List<OrdersItem> orders;

	@JsonProperty("to")
	private String to;

	@JsonProperty("minimumProtocolFee")
	private String minimumProtocolFee;

	@JsonProperty("value")
	private String value;

	@JsonProperty("gasPrice")
	private String gasPrice;
}