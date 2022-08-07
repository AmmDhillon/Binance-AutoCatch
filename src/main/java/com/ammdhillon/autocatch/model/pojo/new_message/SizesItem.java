package com.ammdhillon.autocatch.model.pojo.new_message;

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
public class SizesItem{

	@JsonProperty("size")
	private int size;

	@JsonProperty("w")
	private int W;

	@JsonProperty("h")
	private int H;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("type")
	private String type;
}