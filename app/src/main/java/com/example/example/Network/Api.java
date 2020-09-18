package com.example.example.Network;

import com.bumptech.glide.load.model.Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {


    @Headers({"Authorization: key="+"AAAAITsOZtw:APA91bEh1aV0L-jnrnxLZCEdFUMIxOjYIqSRM0iY6qXEIZrE3x0qm_2bmwu1pMmNnFpUv8dUdVur3CkNA8Dvp88UQ6gyvp9Jmf2r97eaf3m0wP76NDeL1rmvnWL6UT_JX3lwQVX-5cLU","Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendNotification(
        @Body Model root);


}
