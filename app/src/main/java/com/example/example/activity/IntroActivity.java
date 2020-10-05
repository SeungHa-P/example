package com.example.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.example.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class IntroActivity extends AppCompatActivity {

    private Animation ani;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
         ani = AnimationUtils.loadAnimation(this,R.anim.fade_out);




 //--------------------------------PUSH TOKEN 받아 오기 위한----------------------------------------------------------------
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()){
                            Log.w("FCM LOG",task.getException());

                            return;

                        }
                        String token = task.getResult().getToken();
                        //token 저장 (토큰은 싱글톤 및 내장에 저장해야 다른 액티비티에서 사용가능)
                        Log.d("FCM LOG","FCM 토큰 "+token);
                        Toast.makeText(IntroActivity.this,token,Toast.LENGTH_LONG).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        },2000); // 2초후 화면전환

                    }
                });
//-----------------------------------------------------------------------------------------------------------------------------
//---------------------------------topic 사용하기위한 구독 weather 구독--------------------------------------------------------------

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       Log.d("구독","완료");
                    }
                });

//-----------------------------------------------------------------------------------------------------------------------------

    }



}
