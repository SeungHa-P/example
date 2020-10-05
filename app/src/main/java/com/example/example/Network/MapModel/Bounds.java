package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

public class Bounds {

    @SerializedName("southwest")
    private Southwest southwest;

    @SerializedName("northeast")
    private Northeast northeast;


    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }
}
