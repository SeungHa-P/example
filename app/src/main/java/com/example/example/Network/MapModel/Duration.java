package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

public class Duration {
    @SerializedName("text")
    private String text;

    @SerializedName("value")
    private int value;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
