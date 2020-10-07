package com.example.example.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.example.Manager.FcmManager;
import com.example.example.R;
import com.example.example.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    //  터치 이벤트를 사용하기 위해 View.OnTouchListener 상속
    //  4개의 해당 버튼의 애니메이션을 동작시키기 위한 flag 변수
    private boolean pushFlag = false;
    private boolean mapFlag = false;
    private boolean interFlag = false;
    private boolean weatherFlag = false;


    private ActivityMainBinding binding;

    // 각 뷰의 애니메이션
    private Animation fadein, pushBounce, weatherBounce, interBounce, mapBounce;

    //   뷰의 처음 위치 저장
    private float firstX;
    private float firstY;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

//        토큰확인
//        Log.d("token", FirebaseInstanceId.getInstance().getInstanceId() + "");

        fadein = AnimationUtils.loadAnimation(this, R.anim.anim_test);


        // 화면 전환시 버튼 애니메이션 시작--------------
        binding.btnMap.setAnimation(fadein);
        binding.btnInter.setAnimation(fadein);
        binding.btnFoxi.setAnimation(fadein);
        binding.btnWeather.setAnimation(fadein);
        //---------------------------------------


        //  버튼 touch 이벤트 연결
        binding.btn.setOnTouchListener(MainActivity.this);


        //  각 버튼의 애니메이션 연결 및 애니메이션 동작 확인을 위한 리스너 연결----------
        pushBounce = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        pushBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                pushFlag = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pushFlag = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mapBounce = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        mapBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mapFlag = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mapFlag = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        interBounce = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        interBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                interFlag = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                interFlag = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        weatherBounce = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        weatherBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                weatherFlag = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                weatherFlag = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //-----------------------------------------------------------------------

    }
    // touch 좌표를 담을 변
    float oldXvalue;
    float oldYvalue;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //event  << -- Touch 에 대한 동작 x,y 좌표 값 등
            //view.getX,Y  << -- 해당 View 에 대한 좌표값.

            oldXvalue = event.getX();
            oldYvalue = event.getY();
            firstX = v.getX();
            firstY = v.getY();
            pushFlag = false;
            mapFlag = false;
            weatherFlag = false;
            interFlag = false;


        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

//            v.setX(event.getRawX() - oldXvalue);
//            v.setY(event.getRawY() - oldYvalue);
            Log.d("플래그", "푸쉬 = " + pushFlag + "\n날씨 = " + weatherFlag + "\n인터 = " + interFlag + "\n지도 = " + mapFlag);

            //  버튼을 드래그 했을시 버튼이 손을 따라오도록 위치
            //  (버튼의 x좌표위치 + 터치한 x 좌표) - 버튼의 가로 길이 / 2
            v.setX(v.getX() + (event.getX()) - (v.getWidth() / 2));
            v.setY(v.getY() + (event.getY()) - (v.getHeight() / 2));

//--------------------------------------------------------------------------------------------------------------------------

//-------------------------------왼쪽 버튼으로 이동시 ---------------------------------------------------------------------------
            if (((v.getX() < (firstX - 150)) && (v.getY() > (firstY - v.getHeight() / 2)))
                    && ((v.getX() < (firstX - 150)) && (v.getY() < (firstY + v.getHeight() / 2)))
                    &&((v.getX() > binding.btnFoxi.getX()))
            ) {
                if (!pushFlag) {
                    binding.btnFoxi.startAnimation(pushBounce);

                }
            }
//-------------------------------오른쪽 버튼으로 이동시 ---------------------------------------------------------------------------
            if (((v.getX() > (firstX + 150)) && (v.getY() > (firstY - v.getHeight() / 2)))
                    && ((v.getX() > (firstX + 150)) && (v.getY() < (firstY + v.getHeight() / 2)))
                    && ((v.getX() < binding.btnMap.getX()))
            ) {
                if (!mapFlag) {
                    binding.btnMap.startAnimation(mapBounce);
                }
            }
//-------------------------------아래쪾 버튼으로 이동시 ---------------------------------------------------------------------------
            if (((v.getY() > (firstY + 150)) && (v.getX() > (firstX - v.getWidth() / 2)))
                    && ((v.getY() > (firstY + 150)) && (v.getX() < (firstX + v.getWidth() / 2)))
                    && ((v.getY() < binding.btnInter.getY()))
            ) {
                if (!interFlag) {
                    binding.btnInter.startAnimation(interBounce);
                }
            }
//-------------------------------위쪾 버튼으로 이동시 ---------------------------------------------------------------------------
            if (((v.getY() < (firstY - 150)) && (v.getX() > (firstX - v.getWidth() / 2)))
                    && ((v.getY() < (firstY - 150)) && (v.getX() < (firstX + v.getWidth() / 2)))
                    && ((v.getY() > binding.btnWeather.getY()))
            ) {
                if (!weatherFlag) {
                    binding.btnWeather.startAnimation(weatherBounce);
                }

            }
//--------------------------------------------------------------------------------------------------------------------------


        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("좌표", "X = " + event.getX() + " Y = " + event.getY());
            Log.d("좌표2", "X = " + event.getRawX() + " Y = " + event.getRawY());
            Log.d("좌표 뷰", "X = " + v.getX() + " Y = " + v.getY());

            // 버튼을 드래그 한 후 드롭을 했을때 다른 버튼의 충돌을 감지 ( 해당 버튼의 위치 값을 통해 감지 )
            // 가운데 버튼이 다른 해당 버튼의 크기에 들어 왔을때 다음 이벤트(화면전환) 실행
            if (((v.getX() >= (binding.btnFoxi.getX() - (binding.btnFoxi.getWidth() / 2)))
                    && (v.getX() <= (binding.btnFoxi.getX() + (binding.btnFoxi.getWidth() / 2))))
                    && ((v.getY() >= (binding.btnFoxi.getY() - (binding.btnFoxi.getHeight() / 2)))
                    && (v.getY() <= binding.btnFoxi.getY() + (binding.btnFoxi.getHeight() / 2)))) {
                Log.d("좌표 푸쉬버튼", " X = " + binding.btnFoxi.getX()
                        + "width = " + binding.btnFoxi.getWidth() / 2);
                // 가운데 버튼 처음 위치로
                v.setX(firstX);
                v.setY(firstY);

                // 버튼의 해당화면으로 전환
                intent = new Intent(MainActivity.this, PushActivity.class);
                startActivity(intent);



            } else if (((v.getX() >= (binding.btnInter.getX() - (binding.btnInter.getWidth() / 2)))
                    && (v.getX() <= (binding.btnInter.getX() + (binding.btnInter.getWidth() / 2))))
                    && ((v.getY() >= (binding.btnInter.getY() - (binding.btnInter.getHeight() / 2)))
                    && (v.getY() <= binding.btnInter.getY() + (binding.btnInter.getHeight() / 2)))) {
                Log.d("좌표 푸쉬버튼", " X = " + binding.btnFoxi.getX()
                        + "width = " + binding.btnFoxi.getWidth() / 2);
                v.setX(firstX);
                v.setY(firstY);
                intent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(intent);



            } else if (((v.getX() >= (binding.btnWeather.getX() - (binding.btnWeather.getWidth() / 2)))
                    && (v.getX() <= (binding.btnWeather.getX() + (binding.btnWeather.getWidth() / 2))))
                    && ((v.getY() >= (binding.btnWeather.getY() - (binding.btnWeather.getHeight() / 2)))
                    && (v.getY() <= binding.btnWeather.getY() + (binding.btnWeather.getHeight() / 2)))) {
                Log.d("좌표 푸쉬버튼", " X = " + binding.btnFoxi.getX()
                        + "width = " + binding.btnFoxi.getWidth() / 2);
                v.setX(firstX);
                v.setY(firstY);

                // GPS 사용 권한을 위한 퍼미션 등록 작업 requestCode 를 0 으로 맞춰주면
                // 하단 퍼미션 리스너에서 0으로 검사하면 된다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION}, 0);


                    } else {
                        intent = new Intent(MainActivity.this, WeatherActivity.class);
                        startActivity(intent);
                    }
                }



            } else if (((v.getX() >= (binding.btnMap.getX() - (binding.btnMap.getWidth() / 2)))
                    && (v.getX() <= (binding.btnMap.getX() + (binding.btnMap.getWidth() / 2))))
                    && ((v.getY() >= (binding.btnMap.getY() - (binding.btnMap.getHeight() / 2)))
                    && (v.getY() <= binding.btnMap.getY() + (binding.btnMap.getHeight() / 2)))) {
                Log.d("좌표 푸쉬버튼", " X = " + binding.btnFoxi.getX()
                        + "width = " + binding.btnFoxi.getWidth() / 2);
                v.setX(firstX);
                v.setY(firstY);
                intent = new Intent(MainActivity.this, LoadActivity.class);
                startActivity(intent);



            } else {

                // 아무 버튼이랑 충돌 하지 않았을 경우 가운데로 다시 (제자리) 위치
                v.setX(firstX);
                v.setY(firstY);
            }


        }


        return true;
    }


    private void onTriggerBtn(View view) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        FcmManager.context = MainActivity.this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 퍼미션에 대한 결과 리스너
        // requestCode 가 0 이라면 화면전환 ( 퍼미션이 등록 되어있지 않았을때 ) ( 첫 실행해만 작동 )
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent = new Intent(MainActivity.this, WeatherActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "권한을 설정해 주셔야 합니다.", Toast.LENGTH_LONG).show();
                }
                return;
        }

    }
}