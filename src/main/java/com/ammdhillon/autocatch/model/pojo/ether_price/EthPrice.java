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
public class EthPrice{

	@JsonProperty("result")
	private Result result;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;
}