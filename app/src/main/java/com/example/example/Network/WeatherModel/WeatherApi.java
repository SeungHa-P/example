package com.example.example.Network.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
        @GET("data/2.5/weather")
        Call<WeatherResponse> getWeather(
                        @Query("lat")String lat,
                        @Query("lon")String lon,
                        @Query("units")String units,
                        @Query("appid")String appid
                        );

//
//
//    {
//        "coord":{
//        "lon":126.99, "lat":37.49
//    },
//        "weather":[{
//        "id":800, "main":"Clear", "description":"clear sky", "icon":"01d"
//    }],
//        "base":"stations", "main":{
//        "temp":24.73, "feels_like":23.32, "temp_min":24, "temp_max":25, "pressure":1015, "humidity":
//        47
//    },"visibility":10000, "wind":{
//        "speed":3.19, "deg":283
//    },"clouds":{
//        "all":1
//    },"dt":1601282352, "sys":{
//        "type":1, "id":8117, "country":"KR", "sunrise":1601241887, "sunset":1601284831
//    },"timezone":32400, "id":6800035, "name":"Banpobondong", "cod":200
//    }




}
