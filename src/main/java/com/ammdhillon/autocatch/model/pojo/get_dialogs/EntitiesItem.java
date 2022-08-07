package com.ammdhillon.autocatch.model.pojo.get_dialogs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntitiesItem{

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("length")
	private int length;
}