package com.example.example.Network;

public class NotificationModel {
    private String title;
    private String body;

    public NotificationModel(String title, String body){
        this.title = title;
        this.body = body;
    }


    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}