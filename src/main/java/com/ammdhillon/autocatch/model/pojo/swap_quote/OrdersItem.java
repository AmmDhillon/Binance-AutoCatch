package com.ammdhillon.autocatch.model.pojo.swap_quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersItem{

	@JsonProperty("sourcePathId")
	private String sourcePathId;

	@JsonProperty("makerAmount")
	private String makerAmount;

	@JsonProperty("makerToken")
	private String makerToken;

	@JsonProperty("takerAmount")
	private String takerAmount;

	@JsonProperty("takerToken")
	private String takerToken;

	@JsonProperty("source")
	private String source;

	@JsonProperty("type")
	private int type;

	@JsonProperty("fillData")
	private FillData fillData;
}