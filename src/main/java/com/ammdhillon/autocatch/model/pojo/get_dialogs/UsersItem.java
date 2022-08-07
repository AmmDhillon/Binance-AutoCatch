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
public class UsersItem{

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("botReadingHistory")
	private boolean botReadingHistory;

	@JsonProperty("mutualContact")
	private boolean mutualContact;

	@JsonProperty("bot")
	private boolean bot;

	@JsonProperty("langCode")
	private Object langCode;

	@JsonProperty("flags")
	private int flags;

	@JsonProperty("verified")
	private boolean verified;

	@JsonProperty("photo")
	private Object photo;

	@JsonProperty("restrictionReason")
	private Object restrictionReason;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("botInfoVersion")
	private int botInfoVersion;

	@JsonProperty("botCantAddToGroup")
	private boolean botCantAddToGroup;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("classId")
	private int classId;

	@JsonProperty("inlineBot")
	private boolean inlineBot;

	@JsonProperty("deleted")
	private boolean deleted;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("restricted")
	private boolean restricted;

	@JsonProperty("contact")
	private boolean contact;

	@JsonProperty("botInlinePlaceholder")
	private Object botInlinePlaceholder;

	@JsonProperty("self")
	private boolean self;

	@JsonProperty("accessHash")
	private long accessHash;

	@JsonProperty("id")
	private int id;

	@JsonProperty("status")
	private Status status;
}