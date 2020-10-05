package com.example.example.Network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapClient {


    public static MapApi mapApi(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com")
                .build();

        return retrofit.create(MapApi.class);
    }
}

