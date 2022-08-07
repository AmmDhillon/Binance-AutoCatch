package com.ammdhillon.autocatch.model.pojo.crypto_info;

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
public class DataItem{

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("is_active")
	private int isActive;

	@JsonProperty("last_historical_data")
	private String lastHistoricalData;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rank")
	private int rank;

	@JsonProperty("id")
	private int id;

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("platform")
	private Platform platform;

	@JsonProperty("first_historical_data")
	private String firstHistoricalData;
}