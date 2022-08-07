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
public class Status{

	@JsonProperty("error_message")
	private Object errorMessage;

	@JsonProperty("elapsed")
	private int elapsed;

	@JsonProperty("credit_count")
	private int creditCount;

	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("timestamp")
	private String timestamp;

	@JsonProperty("notice")
	private Object notice;
}