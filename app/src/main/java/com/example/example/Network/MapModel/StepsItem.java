package com.example.example.Network.MapModel;

import com.google.gson.annotations.SerializedName;

public class StepsItem {

@SerializedName("duration")
    private Duration duration;

@SerializedName("start_location")
    private StartLocation startLocation;

@SerializedName("distance")
    private Distance distance;

@SerializedName("travel_mode")
    private String travelMode;

@SerializedName("html_instructions")
    private String htmlInstructions;

@SerializedName("end_location")
    private EndLocation endLocation;

@SerializedName("maneuver")
    private String maneuver;

@SerializedName("polyline")
    private Polyline polyline;

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

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }
}
