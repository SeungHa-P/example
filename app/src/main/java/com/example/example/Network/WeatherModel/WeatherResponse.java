package com.example.example.Network.WeatherModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    private List<Weather> weather;


    @SerializedName("main")
    private Wmain wmain;

    @SerializedName("wind")
    private Wind wind;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wmain getWmain() {
        return wmain;
    }

    public void setWmain(Wmain wmain) {
        this.wmain = wmain;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
