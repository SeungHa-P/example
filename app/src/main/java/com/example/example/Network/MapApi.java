package com.example.example.Network;

        import android.text.Layout;

        import com.example.example.Network.MapModel.DirectionResponses;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface MapApi {
    @GET("maps/api/directions/json")
    Call<DirectionResponses> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("mode") String mode,
            @Query("key") String key

    );
}
