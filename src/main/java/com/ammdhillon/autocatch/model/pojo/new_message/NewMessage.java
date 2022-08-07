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
public class NewMessage{

	@JsonProperty("toId")
	private ToId toId;

	@JsonProperty("date")
	private int date;

	@JsonProperty("viaBotId")
	private int viaBotId;

	@JsonProperty("flags")
	private int flags;

	@JsonProperty("id")
	private int id;

	@JsonProperty("replyToMsgId")
	private int replyToMsgId;

	@JsonProperty("media")
	private Media media;

	@JsonProperty("message")
	private String message;

	@JsonProperty("fromId")
	private int fromId;

	@JsonProperty("editDate")
	private int editDate;

	@JsonProperty("views")
	private int views;
}