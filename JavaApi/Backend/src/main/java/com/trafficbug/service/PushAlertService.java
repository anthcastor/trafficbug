package com.trafficbug.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import com.trafficbug.model.AlertPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class PushAlertService {

    @Autowired
    private DeviceTokenService deviceTokenService;

    public void dispatchAlert(AlertPayload payload) {
        String token = deviceTokenService.getTokenByDeviceId(payload.getDeviceId());

        Message message = Message.builder()
                .putData("alertLevel", payload.getAlertLevel())
                .putData("message", payload.getMessage())
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            // Handle failed send
        }

    }
}
