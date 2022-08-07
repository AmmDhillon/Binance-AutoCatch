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
public class Photo{

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("photoBig")
	private PhotoBig photoBig;

	@JsonProperty("photoSmall")
	private PhotoSmall photoSmall;

	@JsonProperty("photoId")
	private long photoId;
}