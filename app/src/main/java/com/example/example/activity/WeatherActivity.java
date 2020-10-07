package com.example.example.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import com.example.example.Fragment.FragementNow;
import com.example.example.Fragment.FragmentDescription;
import com.example.example.Fragment.FragmentHumidity;
import com.example.example.Fragment.FragmentWind;

import com.example.example.Network.WeatherModel.WeatherApi;
import com.example.example.Network.WeatherModel.WeatherClient;
import com.example.example.Network.WeatherModel.WeatherResponse;
import com.example.example.R;
import com.example.example.Fragment.adapter.WeatherAdapter;
import com.example.example.databinding.ActivityWeatherBinding;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherActivity extends AppCompatActivity {
    private ActivityWeatherBinding binding;
    double longitude;
    double latitude;
    private final int count=4;
    String apiKEY = "2a0a73b3457fabd61dde1f052cadc36f";


    private int autoCount = 4;
    Timer timer;

    final int DELAY = 500;
    final int PERIOD = 1500;

    private  ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_weather);
        binding.setActivity(this);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findWeather();

        binding.redirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findWeather();
            }
        });

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(autoCount == count*2) {
                    autoCount = count;
                }
                binding.vPager.setCurrentItem(autoCount++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY, PERIOD);


    }


private void findWeather(){

    findGPS();

    String addr[] = addressGPS().split(" ");

    String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&appid="+apiKEY;
    Log.d("api",url);
    binding.txtAddress.setText(addr[1]+" "+addr[2]);
    Log.d("위경도",latitude+"      "+longitude);
    WeatherApi weatherApi = WeatherClient.weatherApi(WeatherActivity.this);
    weatherApi.getWeather(""+latitude,""+longitude,"metric",apiKEY)
            .enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                    Toast.makeText(WeatherActivity.this,"성공",Toast.LENGTH_LONG).show();
                    FragementNow.now = response.body().getWmain().getTemp();
                    FragmentDescription.imgUri= "https://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png";
                    FragmentDescription.description=transferWeather(response.body().getWeather().get(0).getDescription());
                    FragmentHumidity.txtHumidity=response.body().getWmain().getHumidity();
                    FragmentWind.txtWind = response.body().getWind().getSpeed();
                    binding.now.setText("현재 : "+response.body().getWmain().getTemp()+"º");
                    binding.txtDown.setText("↓ "+response.body().getWmain().getTempMin()+"º");
                    binding.txtUp.setText("↑ "+response.body().getWmain().getTempMax()+"º");

                    WeatherAdapter weatherAdapter = new WeatherAdapter(getSupportFragmentManager(),count);

                    binding.vPager.setAdapter(weatherAdapter);
                    binding.indicator.setViewPager(binding.vPager);

                    binding.vPager.setCurrentItem(count);
                    binding.indicator.createIndicators(4,0);




                    binding.vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Log.d("PageScrolled", "아아아    " +position);
                        }

                        @Override
                        public void onPageSelected(int position) {
                            Log.d("PageSelected", "하하하    "+position);
                            if(position < count){
                                binding.vPager.setCurrentItem(position + count,false);
                                autoCount=position;

                            }else if(position >= count*2){
                                binding.vPager.setCurrentItem(position - count,false);
                                autoCount=position;
                            }
                            autoCount=position;
                            binding.indicator.animatePageSelected(position%count);

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            Log.d("PageScrollStateChaged", "효효효     "+ state);
                        }
                    });



                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.d("메세제",t.getMessage());
                    Toast.makeText(WeatherActivity.this,"실패",Toast.LENGTH_LONG).show();
                }
            });

}



//------------------------------OpenWeather API 에서 받아오는 Description 의 문자를 한글로 파싱하기 위한 메소드 ------------------------------------------
private String transferWeather(String weather){
        weather = weather.toLowerCase();
        if("haze".equals(weather)){
            return "안개";
        }else if("fog".equals(weather)){
            return "안개";
        }else if("clouds".equals(weather)){
            return "구름";
        }else if ("few clouds".equals(weather)){
            return "구름 조금";
        }else if ("scattered clouds".equals(weather)){
            return "구름 많음";
        }else if ("broken clouds".equals(weather)){
            return "구름 많음";
        }else if ("overcast clouds".equals(weather)){
            return "구름 많음";
        }else if ("clear sky".equals(weather)){
            return "맑음";
        }else if ("light rain".equals(weather)){
            return "비";
        }

        return "";
}

//-----------------------------------------------------------------------------------------------------------------------------------------------





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

            finish();
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


