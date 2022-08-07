package com.ammdhillon.autocatch.echo;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.*;
import org.telegram.api.update.encrypted.TLUpdateEncryptedChatTyping;
import org.telegram.api.update.encrypted.TLUpdateEncryptedMessagesRead;
import org.telegram.api.update.encrypted.TLUpdateEncryption;
import org.telegram.api.update.encrypted.TLUpdateNewEncryptedMessage;
import org.telegram.api.updates.TLUpdateShortChatMessage;
import org.telegram.api.updates.TLUpdateShortMessage;
import org.telegram.api.updates.TLUpdateShortSentMessage;
import org.telegram.api.user.TLAbsUser;

import java.util.List;

public abstract class OnNotificationAbstract {

    protected void onTLUpdateChatParticipantsCustom(TLUpdateChatParticipants update) {
    }

    protected void onTLUpdateNewMessageCustom(TLUpdateNewMessage update) {
    }

    protected void onTLUpdateChannelNewMessageCustom(TLUpdateChannelNewMessage update) {
    }

    protected void onTLUpdateChannelCustom(TLUpdateChannel update) {
    }

    protected void onTLUpdateBotInlineQueryCustom(TLUpdateBotInlineQuery update) {
    }

    protected void onTLUpdateBotInlineSendCustom(TLUpdateBotInlineSend update) {
    }

    protected void onTLUpdateChannelMessageViewsCustom(TLUpdateChannelMessageViews update) {
    }

    protected void onTLUpdateChannelPinnedMessageCustom(TLUpdateChannelPinnedMessage update) {
    }

    protected void onTLUpdateChatAdminCustom(TLUpdateChatAdmin update) {
    }

    protected void onTLUpdateChatParticipantAddCustom(TLUpdateChatParticipantAdd update) {
    }

    protected void onTLUpdateChatParticipantAdminCustom(TLUpdateChatParticipantAdmin update) {
    }

    protected void onTLUpdateChatParticipantDeleteCustom(TLUpdateChatParticipantDelete update) {
    }

    protected void onTLUpdateChatUserTypingCustom(TLUpdateChatUserTyping update) {
    }

    protected void onTLUpdateContactLinkCustom(TLUpdateContactLink update) {
    }

    protected void onTLUpdateContactRegisteredCustom(TLUpdateContactRegistered update) {
    }

    protected void onTLUpdateDcOptionsCustom(TLUpdateDcOptions update) {
    }

    protected void onTLUpdateDeleteChannelMessagesCustom(TLUpdateDeleteChannelMessages update) {
    }

    protected void onTLUpdateDeleteMessagesCustom(TLUpdateDeleteMessages update) {
    }

    protected void onTLUpdateEditChannelMessageCustom(TLUpdateEditChannelMessage update) {
    }

    protected void onTLUpdateMessageIdCustom(TLUpdateMessageId update) {
    }

    protected void onTLUpdateNewStickerSetCustom(TLUpdateNewStickerSet update) {
    }

    protected void onTLUpdateNotifySettingsCustom(TLUpdateNotifySettings update) {
    }

    protected void onTLUpdatePrivacyCustom(TLUpdatePrivacy update) {
    }

    protected void onTLUpdateReadChannelInboxCustom(TLUpdateReadChannelInbox update) {
    }

    protected void onTLUpdateReadMessagesContentsCustom(TLUpdateReadMessagesContents update) {
    }

    protected void onTLUpdateReadMessagesInboxCustom(TLUpdateReadMessagesInbox update) {
    }

    protected void onTLUpdateReadMessagesOutboxCustom(TLUpdateReadMessagesOutbox update) {
    }

    protected void onTLUpdateSavedGifsCustom(TLUpdateSavedGifs update) {
    }

    protected void onTLUpdateServiceNotificationCustom(TLUpdateServiceNotification update) {
    }

    protected void onTLUpdateStickerSetsCustom(TLUpdateStickerSets update) {
    }

    protected void onTLUpdateStickerSetsOrderCustom(TLUpdateStickerSetsOrder update) {
    }

    protected void onTLUpdateUserBlockedCustom(TLUpdateUserBlocked update) {
    }

    protected void onTLUpdateUserNameCustom(TLUpdateUserName update) {
    }

    protected void onTLUpdateUserPhoneCustom(TLUpdateUserPhone update) {
    }

    protected void onTLUpdateUserPhotoCustom(TLUpdateUserPhoto update) {
    }

    protected void onTLUpdateUserStatusCustom(TLUpdateUserStatus update) {
    }

    protected void onTLUpdateUserTypingCustom(TLUpdateUserTyping update) {
    }

    protected void onTLUpdateWebPageCustom(TLUpdateWebPage update) {
    }

    protected void onTLFakeUpdateCustom(TLFakeUpdate update) {
    }

    protected void onTLUpdateShortMessageCustom(TLUpdateShortMessage update) {
    }

    protected void onTLUpdateShortChatMessageCustom(TLUpdateShortChatMessage update) {
    }

    protected void onTLUpdateShortSentMessageCustom(TLUpdateShortSentMessage update) {
    }

    protected void onTLAbsMessageCustom(TLAbsMessage message) {
    }

    protected void onUsersCustom(List<TLAbsUser> users) {
    }

    protected void onChatsCustom(List<TLAbsChat> chats) {
    }

    protected void onTLUpdateEncryptionCustom(TLUpdateEncryption update) {
    }

    protected void onTLUpdateEncryptedMessagesReadCustom(TLUpdateEncryptedMessagesRead update) {
    }

    protected void onTLUpdateNewEncryptedMessageCustom(TLUpdateNewEncryptedMessage update) {
    }

    protected void onTLUpdateEncryptedChatTypingCustom(TLUpdateEncryptedChatTyping update) {
    }

    protected void onTLUpdateConfigCustom(TLUpdateConfig update) {
    }

    protected void onTLUpdateDraftMessageCustom(TLUpdateDraftMessage update) {
    }

    protected void onTLUpdatePtsChangedCustom(TLUpdatePtsChanged update) {
    }

    protected void onTLUpdateReadChannelOutboxCustom(TLUpdateReadChannelOutbox update) {
    }

    protected void onTLUpdateReadFeaturedStickersCustom(TLUpdateReadFeaturedStickers update) {
    }

    protected void onTLUpdateRecentStickersCustom(TLUpdateRecentStickers update) {
    }

    protected void onTLUpdateBotCallbackQueryCustom(TLUpdateBotCallbackQuery update) {
    }

    protected void onTLUpdateEditMessageCustom(TLUpdateEditMessage update) {
    }

    protected void onTLUpdateInlineBotCallbackQueryCustom(TLUpdateInlineBotCallbackQuery update) {
    }

    protected void onTLUpdateChannelWebPageCustom(TLUpdateChannelWebPage update) {
    }

    protected void onTLUpdatePhoneCallCustom(TLUpdatePhoneCall update) {
    }

    protected void onTLUpdateDialogPinnedCustom(TLUpdateDialogPinned update) {
    }

    protected void onTLUpdatePinnedDialogsCustom(TLUpdatePinnedDialogs update) {
    }

    protected void onTLUpdateBotWebhookJSONCustom(TLUpdateBotWebhookJSON update) {
    }

    protected void onTLUpdateBotWebhookJSONQueryCustom(TLUpdateBotWebhookJSONQuery update) {
    }

    protected void onTLUpdateBotShippingQueryCustom(TLUpdateBotShippingQuery update) {
    }

    protected void onTLUpdateBotPrecheckoutQueryCustom(TLUpdateBotPrecheckoutQuery update) {
    }
}
