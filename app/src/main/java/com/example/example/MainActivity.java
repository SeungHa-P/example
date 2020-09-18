package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    Button btnload,btnpush,btnweather;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("token", FirebaseInstanceId.getInstance().getInstanceId()+"");
        btnload=findViewById(R.id.btn_map);
        btnweather=findViewById(R.id.btn_weather);
        btnpush=findViewById(R.id.btn_foxi);

//        FcmManager.context=MainActivity.this;


        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(MainActivity.this,LoadActivity.class);
                startActivity(intent);

            }
        });

        btnpush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,PushActivity.class);
                startActivity(intent);

            }
        });

        btnweather.setOnClickListener(new View.OnClickListener() {
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