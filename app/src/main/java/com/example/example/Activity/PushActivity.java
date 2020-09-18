package com.example.example.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.example.Manager.FcmManager;
import com.example.example.Network.Api;
import com.example.example.Network.ApiClient;
import com.example.example.Network.Model;
import com.example.example.Network.NotificationModel;
import com.example.example.R;
import com.example.example.databinding.ActivityPushBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PushActivity extends AppCompatActivity {


    private ActivityPushBinding binding;
    private String fcmToken;


//    private static final String FCM_URL = "http://fcm.googleapis.com/fcm/send";
//    private static final String SERVER_KEY ="AAAAITsOZtw:APA91bEh1aV0L-jnrnxLZCEdFUMIxOjYIqSRM0iY6qXEIZrE3x0qm_2bmwu1pMmNnFpUv8dUdVur3CkNA8Dvp88UQ6gyvp9Jmf2r97eaf3m0wP76NDeL1rmvnWL6UT_JX3lwQVX-5cLU";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_push);
        binding.setActivity(this);
        setTitle("폭시");
        FcmManager.context = PushActivity.this;

        getMyToken();


        binding.tokenTxt.setText(fcmToken);
        binding.pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

//                    JSONObject root = new JSONObject();
//                    JSONObject notification = new JSONObject();
//                    notification.put("body", binding.msgEdt.getText().toString());
//                    notification.put("title","알림");
//                    root.put("notification",notification);
//                    root.put("to","/topics/weather");

                    sendNotificationToUser();



                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });










    }

    private void sendNotificationToUser(){
    Model model = new Model("topics/weather",new NotificationModel("알림",binding.msgEdt.getText().toString()));

        Api api = ApiClient.getClient().create(Api.class);

        Call<ResponseBody> responseBodyCall =api.sendNotification((com.bumptech.glide.load.model.Model) model);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(PushActivity.this,"성공",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PushActivity.this,"실패",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getMyToken(){

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                     fcmToken = task.getResult().getToken();
                    }
                });
    }

}