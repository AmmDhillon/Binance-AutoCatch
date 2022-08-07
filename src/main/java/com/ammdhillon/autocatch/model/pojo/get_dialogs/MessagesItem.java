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
public class MessagesItem {

	@JsonProperty("toId")
	private ToId toId;

	@JsonProperty("date")
	private Long date;

	@JsonProperty("viaBotId")
	private int viaBotId;

	@JsonProperty("silent")
	private boolean silent;

	@JsonProperty("chatId")
	private int chatId;

	@JsonProperty("flags")
	private int flags;

	@JsonProperty("media")
	private Object media;

	@JsonProperty("message")
	private String message;

	@JsonProperty("fromId")
	private int fromId;

	@JsonProperty("editDate")
	private int editDate;

	@JsonProperty("sent")
	private boolean sent;

	@JsonProperty("forwarded")
	private boolean forwarded;

	@JsonProperty("mention")
	private boolean mention;

	@JsonProperty("unreadContent")
	private boolean unreadContent;

	@JsonProperty("fwdFrom")
	private Object fwdFrom;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("post")
	private boolean post;

	@JsonProperty("entities")
	private List<EntitiesItem> entities;

	@JsonProperty("replyMarkup")
	private Object replyMarkup;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("replyToMsgId")
	private int replyToMsgId;

	@JsonProperty("reply")
	private boolean reply;

	@JsonProperty("views")
	private int views;

	@JsonProperty("replyToMessageId")
	private int replyToMessageId;

	@JsonProperty("action")
	private Action action;
}