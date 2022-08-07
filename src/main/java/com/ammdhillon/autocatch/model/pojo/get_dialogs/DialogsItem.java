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
public class DialogsItem{

	@JsonProperty("readOutboxMaxId")
	private int readOutboxMaxId;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("pinned")
	private boolean pinned;

	@JsonProperty("readInboxMaxId")
	private int readInboxMaxId;

	@JsonProperty("peer")
	private Peer peer;

	@JsonProperty("draft")
	private Object draft;

	@JsonProperty("topMessage")
	private int topMessage;

	@JsonProperty("unreadCount")
	private int unreadCount;

	@JsonProperty("notifySettings")
	private NotifySettings notifySettings;

	@JsonProperty("pts")
	private int pts;
}