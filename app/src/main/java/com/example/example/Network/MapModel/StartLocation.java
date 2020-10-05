package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

public class StartLocation {
    @SerializedName("lng")
    private double lng;

    @SerializedName("lat")
    private double lat;


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
