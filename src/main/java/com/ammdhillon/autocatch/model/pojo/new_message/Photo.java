package com.ammdhillon.autocatch.model.pojo.new_message;

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
public class Photo{

	@JsonProperty("date")
	private int date;

	@JsonProperty("sizes")
	private List<SizesItem> sizes;

	@JsonProperty("flags")
	private int flags;

	@JsonProperty("accessHash")
	private long accessHash;

	@JsonProperty("id")
	private long id;
}