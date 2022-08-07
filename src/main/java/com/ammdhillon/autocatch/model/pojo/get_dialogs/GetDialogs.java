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
public class GetDialogs{

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("chats")
	private List<ChatsItem> chats;

	@JsonProperty("count")
	private int count;

	@JsonProperty("messages")
	private List<MessagesItem> messages;

	@JsonProperty("dialogs")
	private List<DialogsItem> dialogs;

	@JsonProperty("users")
	private List<UsersItem> users;
}