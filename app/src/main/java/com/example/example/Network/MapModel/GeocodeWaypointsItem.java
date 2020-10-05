package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodeWaypointsItem {

    @SerializedName("types")
    private List<String> types;

    @SerializedName("geocoder_status")
    private String geocoderStatus;

    @SerializedName("place_id")
    private String placeId;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
