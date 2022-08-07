package com.ammdhillon.autocatch.model.pojo.ether_price;

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
public class Result{

	@JsonProperty("ethusd")
	private String ethusd;

	@JsonProperty("ethbtc")
	private String ethbtc;

	@JsonProperty("ethusd_timestamp")
	private String ethusdTimestamp;

	@JsonProperty("ethbtc_timestamp")
	private String ethbtcTimestamp;
}