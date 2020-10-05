package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoutesItem {

    @SerializedName("summary")
    private String summary;

    @SerializedName("copyrights")
    private String copyrights;

    @SerializedName("legs")
    private List<LegsItem> legs;

    @SerializedName("warnings")
    private List<Object> warnings;

    @SerializedName("bounds")
    private Bounds bounds;

    @SerializedName("overview_polyline")
    private OverviewPolyline overviewPolyline;

    @SerializedName("waypoint_order")
    private List<Object> waypointOrder;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<LegsItem> getLegs() {
        return legs;
    }

    public void setLegs(List<LegsItem> legs) {
        this.legs = legs;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }
}
