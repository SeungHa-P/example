package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

public class EndLocation {

    @SerializedName("lng")
    private double lng;

    @SerializedName("lat")
    private double let;


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLet() {
        return let;
    }

    public void setLet(double let) {
        this.let = let;
    }
}
