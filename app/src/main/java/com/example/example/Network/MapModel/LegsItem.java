package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LegsItem {
    @SerializedName("duration")
    private Duration duration;
    @SerializedName("start_location")
    private StartLocation startLocation;
    @SerializedName("distance")
    private Distance distance;
    @SerializedName("start_address")
    private String startAddress;
    @SerializedName("end_location")
    private EndLocation endLocation;
    @SerializedName("end_address")
    private String endAddress;
    @SerializedName("via_waypoint")
    private List<Object> viaWaypoint;
    @SerializedName("steps")
    private List<StepsItem> steps;
    @SerializedName("traffic_speed_entry")
    private List<Object> trafficSpeedEntry;


    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

    public List<StepsItem> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsItem> steps) {
        this.steps = steps;
    }

    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }
}
