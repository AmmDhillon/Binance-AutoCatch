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
public class Media{

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("webPage")
	private WebPage webPage;

	@JsonProperty("document")
	private Document document;

	@JsonProperty("caption")
	private String caption;

//	@JsonProperty("photo")
//	private Photo photo;
}