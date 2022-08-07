package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.model.pojo.PushNotificationRequest;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class FCMService {

    @Autowired
    private DataService dataService;

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response + " msg " + jsonOutput);
    }

    public void sendMessageWithoutData(PushNotificationRequest request) {
        if (!dataService.getNotifications()) {
            System.out.println("Notifications have been disabled");
            return;
        }

        try {
            Message message = getPreconfiguredMessageWithoutData(request);
            String response = sendAndGetResponse(message);
            logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
//                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound("")
                        .setColor("#FFFFFF").setTag(topic).build()).build();
    }

    private ApnsConfig getAPNSConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getAPNSConfig(request.getTopic());

        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getMessage())
                .build();

        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(notification);
    }
}