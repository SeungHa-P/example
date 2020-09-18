package com.example.example.Network;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("to")
    private String token;

    @SerializedName("notification")
    private NotificationModel notification;


    public Model(String token,NotificationModel notification){
        this.notification = notification;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setNotification(NotificationModel notification) {
        this.notification = notification;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NotificationModel getNotification() {
        return notification;
    }
}
