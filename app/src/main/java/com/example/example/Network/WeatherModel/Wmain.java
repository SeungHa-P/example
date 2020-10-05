package com.example.example.Network.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Wmain {

    @SerializedName("temp")
    private String temp;

    @SerializedName("feels_like")
    private String feelsLike;

    @SerializedName("temp_min")
    private String tempMin;

    @SerializedName("temp_max")
    private String tempMax;

    @SerializedName("pressure")
    private String preesure;

    @SerializedName("humidity")
    private String humidity;


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getPreesure() {
        return preesure;
    }

    public void setPreesure(String preesure) {
        this.preesure = preesure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
