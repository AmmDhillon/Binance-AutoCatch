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
public class Platform{

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("name")
	private String name;

	@JsonProperty("token_address")
	private String tokenAddress;

	@JsonProperty("id")
	private int id;

	@JsonProperty("slug")
	private String slug;
}