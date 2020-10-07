package com.example.example.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.example.Manager.Subway;
import com.example.example.R;
import com.example.example.database.RoomDB;
import com.example.example.database.SubwayDao;
import com.example.example.database.SubwayDatabase;
import com.example.example.database.SubwayDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private Animation ani;
    //public SharedPreferences prefs;
    private SubwayDatabase subwayDatabase;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ani = AnimationUtils.loadAnimation(this, R.anim.fade_out);


//==================== 최초 앱 실행 확인 ===============================
        SharedPreferences prefs;
        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            Log.d("Pref 테스트", "처음실행");
            //======== SQLite 데이터 삽입 =============
            //insertSubway();
            //===============================

            roomDbOpen();

            prefs.edit().putBoolean("isFirstRun", false).apply();

        } else {
            Log.d("Pref 테스트", "NOT처음실행");
        }

//==================================================================


        //--------------------------------PUSH TOKEN 받아 오기 위한----------------------------------------------------------------
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM LOG", task.getException());

                            return;

                        }
                        String token = task.getResult().getToken();
                        //token 저장 (토큰은 싱글톤 및 내장에 저장해야 다른 액티비티에서 사용가능)
                        Log.d("FCM LOG", "FCM 토큰 " + token);
                        Toast.makeText(IntroActivity.this, token, Toast.LENGTH_LONG).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                startActivity(intent);

                                finish();

                            }
                        }, 2000); // 2초후 화면전환

                    }
                });
//-----------------------------------------------------------------------------------------------------------------------------
//---------------------------------topic 사용하기위한 구독 weather 구독--------------------------------------------------------------

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("구독", "완료");
                    }
                });

//-----------------------------------------------------------------------------------------------------------------------------

    }

//=========================== Local DB 데이터 삽입 =============================================
    public void insertSubway() {

        subwayDatabase = SubwayDatabase.getInstance(this);
        database = subwayDatabase.getWritableDatabase();

        String json = "";
        try {
            InputStream is = getAssets().open("subway.json");

            long fileSize = is.available();
            byte[] buffer = new byte[(int) fileSize];
            Log.d("인트값", "" + fileSize);
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jj = jsonArray.getJSONObject(i);

                Subway subway = new Subway();
                subway.setStation(jj.getString("statn_nm"));
                subway.setAddress(jj.getString("adres"));

                database.execSQL(
                        "INSERT INTO subway VALUES('" +
                                subway.getStation() + "','" + subway.getAddress() + "');");

              }
           } catch (JSONException e) {
        }
        database.close();

    }
//=============================================================================================


    //====================  Room 사용 =========================
    public void roomDbOpen(){
        RoomDB db = RoomDB.getAppDatabase(this);


        String json = "";
        try {
            InputStream is = getAssets().open("subway.json");

            long fileSize = is.available();
            byte[] buffer = new byte[(int) fileSize];
            Log.d("인트값", "" + fileSize);
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jj = jsonArray.getJSONObject(i);

                SubwayDto subwayDto = new SubwayDto();

                subwayDto.setStation(jj.getString("statn_nm"));
                subwayDto.setAddress(jj.getString("adres"));

                db.subwayDao().insert(subwayDto);


            }
        } catch (JSONException e) {
        }


    }

    //========================================================


}
