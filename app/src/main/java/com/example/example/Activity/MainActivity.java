package com.example.example.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.example.Manager.FcmManager;
import com.example.example.R;
import com.example.example.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        Log.d("token", FirebaseInstanceId.getInstance().getInstanceId()+"");



        binding.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(MainActivity.this,LoadActivity.class);
                startActivity(intent);

            }
        });

        binding.btnFoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,PushActivity.class);
                startActivity(intent);

            }
        });

        binding.btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(intent);

            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        FcmManager.context=MainActivity.this;
    }
}