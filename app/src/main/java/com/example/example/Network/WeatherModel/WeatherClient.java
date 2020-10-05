package com.example.example.Network.WeatherModel;

import android.content.Context;

import com.example.example.Network.MapApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    //https://api.openweathermap.org/


    public static WeatherApi weatherApi(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org")
                .build();

        return retrofit.create(WeatherApi.class);
    }
}
