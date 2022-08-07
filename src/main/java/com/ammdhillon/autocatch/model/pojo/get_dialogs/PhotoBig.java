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
public class PhotoBig{

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("dcId")
	private int dcId;

	@JsonProperty("volumeId")
	private long volumeId;

	@JsonProperty("secret")
	private long secret;

	@JsonProperty("localId")
	private int localId;
}