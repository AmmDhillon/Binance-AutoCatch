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
public class WebPage{

	@JsonProperty("cachedPage")
	private Object cachedPage;

	@JsonProperty("display_url")
	private String displayUrl;

	@JsonProperty("embed_url")
	private Object embedUrl;

	@JsonProperty("author")
	private Object author;

	@JsonProperty("document")
	private Object document;

	@JsonProperty("flags")
	private int flags;

	@JsonProperty("description")
	private String description;

//	@JsonProperty("photo")
//	private Photo photo;

	@JsonProperty("type")
	private String type;

	@JsonProperty("title")
	private String title;

	@JsonProperty("url")
	private String url;

	@JsonProperty("site_name")
	private String siteName;

	@JsonProperty("duration")
	private int duration;

	@JsonProperty("embed_width")
	private int embedWidth;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("id")
	private long id;

	@JsonProperty("embed_type")
	private Object embedType;

	@JsonProperty("embed_height")
	private int embedHeight;

	@JsonProperty("hash")
	private int hash;
}