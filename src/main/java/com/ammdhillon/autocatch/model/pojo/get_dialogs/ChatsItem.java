package com.ammdhillon.autocatch.model.pojo.get_dialogs;

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
public class ChatsItem{

	@JsonProperty("date")
	private int date;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("flags")
	private int flags;

//	@JsonProperty("photo")
//	private Photo photo;

	@JsonProperty("accessHash")
	private long accessHash;

	@JsonProperty("id")
	private int id;

	@JsonProperty("restrictionReason")
	private Object restrictionReason;

	@JsonProperty("title")
	private String title;

	@JsonProperty("version")
	private int version;

	@JsonProperty("username")
	private String username;
}