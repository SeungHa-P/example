package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectionResponses {

    @SerializedName("routes")
    private List<RoutesItem> routes;

    @SerializedName("geocoded_waypoints")
    private List<GeocodeWaypointsItem> geocodeWaypointsItems;

    @SerializedName("status")
    private String status;

    public List<RoutesItem> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesItem> routes) {
        this.routes = routes;
    }

    public List<GeocodeWaypointsItem> getGeocodeWaypointsItems() {
        return geocodeWaypointsItems;
    }

    public void setGeocodeWaypointsItems(List<GeocodeWaypointsItem> geocodeWaypointsItems) {
        this.geocodeWaypointsItems = geocodeWaypointsItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
