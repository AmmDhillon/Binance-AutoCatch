package com.ammdhillon.autocatch.model.pojo.get_dialogs;

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
public class Document{

	@JsonProperty("date")
	private int date;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("size")
	private int size;

	@JsonProperty("dcId")
	private int dcId;

	@JsonProperty("thumb")
	private Thumb thumb;

	@JsonProperty("accessHash")
	private long accessHash;

	@JsonProperty("attributes")
	private List<AttributesItem> attributes;

	@JsonProperty("id")
	private long id;

	@JsonProperty("mimeType")
	private String mimeType;

	@JsonProperty("version")
	private int version;
}