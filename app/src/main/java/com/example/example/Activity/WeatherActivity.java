package com.example.example.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example.Fragment.FragementNow;
import com.example.example.Fragment.FragmentDescription;
import com.example.example.Fragment.FragmentHumidity;
import com.example.example.Fragment.FragmentWind;
import com.example.example.R;
import com.example.example.Fragment.adapter.WeatherAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;


public class WeatherActivity extends AppCompatActivity {

    double longitude;
    double latitude;
    String apiKEY = "2a0a73b3457fabd61dde1f052cadc36f";

    String iconName = "";
    String nowTemp;
    String maxTemp;
    String minTemp;
    String humidity = "";
    String speed = "";
    String main = "";
    String description="";

    private  TextView textView;
    private  TextView mintxt,maxtxt;


    private  ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findGPS();

        textView = findViewById(R.id.txt_address);
        mintxt = findViewById(R.id.txt_down);
        maxtxt = findViewById(R.id.txt_up);

        String addr[] = addressGPS().split(" ");


        textView.setText(addr[1]+" "+addr[2]);

        getWeatherData();

        viewPager = findViewById(R.id.vPager);



}

private void getWeatherData(){
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&appid="+apiKEY;
        Log.d("api",url);
        ReceiveWeatherTask receiveWeatherTask = new ReceiveWeatherTask();
        receiveWeatherTask.execute(url);


}



private class ReceiveWeatherTask extends AsyncTask<String, Void, JSONObject>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(strings[0]).openConnection();

            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader in = new BufferedReader(reader);

                String read;
                while ((read = in.readLine())!=null){
                    JSONObject jsonObject = new JSONObject(read);
                    Log.d("ggg","afas");
                    return jsonObject;
                }
            }else{
                return null;
            }
            return null;



        }catch (Exception e){
            Log.i("urlError",e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.i("json", jsonObject.toString());

        if(jsonObject != null){


            try{

                iconName = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                nowTemp = jsonObject.getJSONObject("main").getString("temp");
                maxTemp =jsonObject.getJSONObject("main").getString("temp_max");
                minTemp = jsonObject.getJSONObject("main").getString("temp_min");
                humidity = jsonObject.getJSONObject("main").getString("humidity");
                speed=jsonObject.getJSONObject("wind").getString("speed");
                main = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
                description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

            }catch (JSONException e){
                Log.d("jsonException", e.toString());
            }
            description = transferWeather(description);

            final String msg = description + " 습도 : "+ humidity +"% \n풍속 : "+speed+"m/s\n현재 온도 : "+nowTemp+
                    "\n최저 온도 : "+minTemp+"\n최고 온도 : "+maxTemp;


            mintxt.setText(minTemp+"도");
            maxtxt.setText(maxTemp+"도");

            Toast.makeText(WeatherActivity.this,msg,Toast.LENGTH_LONG).show();
            Log.d("icon",iconName);
            FragementNow.now = minTemp;
            FragmentDescription.imgUri= "https://openweathermap.org/img/w/"+iconName+".png";
            FragmentDescription.description=description;
            FragmentHumidity.txtHumidity=humidity;
            FragmentWind.txtWind = speed;

            WeatherAdapter weatherAdapter = new WeatherAdapter(getSupportFragmentManager());
            viewPager.setAdapter(weatherAdapter);
        }
    }
}

private String transferWeather(String weather){
        weather = weather.toLowerCase();
        if(weather.equals("haze")){
            return "안개";
        }else if(weather.equals("fog")){
            return "안개";
        }else  if(weather.equals("clouds")){
            return "구름";
        }else if (weather.equals("few clouds")){
            return "구름 조금";
        }else if (weather.equals("scattered clouds")){
            return "구름 많음";
        }else if (weather.equals("broken clouds")){
            return "구름 많음";
        }else if (weather.equals("overcast clouds")){
            return "구름 많음";
        }else if (weather.equals("clear sky")){
            return "맑음";
        }else if (weather.equals("light rain")){
            return "비";
        }

        return "";
}






    public String addressGPS(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;
        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    3
            );
        }catch (IOException e){
            Toast.makeText(this,"지오코더 서비스 불가",Toast.LENGTH_LONG).show();
            return "지오코더 서비스 불가";
        }catch (IllegalArgumentException e){
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_SHORT).show();
            return "잘못된 GPS 좌표";
        }
        Address address = addresses.get(0);
        return address.getAddressLine(0).toString();
    }



    public void findGPS(){
        final LocationManager lm =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }else{
            Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            //  String provider = location.getProvider();
         longitude = location.getLongitude();
        latitude = location.getLatitude();

            Toast.makeText(WeatherActivity.this,"위도 : "+latitude+ "\n" +"경도 : "+longitude,Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();

        }
        return true;
    }
}