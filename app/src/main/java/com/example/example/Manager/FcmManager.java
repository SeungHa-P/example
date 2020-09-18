package com.example.example.Manager;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.example.Activity.IntroActivity;
import com.example.example.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmManager extends FirebaseMessagingService {


    public static Context context;
    public static AlertDialog dialog;

    @Override
    public void onNewToken(String s) {
        Log.d("FCM   ", toString());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {


            String messageBody = remoteMessage.getNotification().getBody();
            String messageTitle = remoteMessage.getNotification().getTitle();

            Intent intent = new Intent(this, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "Channel ID";

            Uri defalutSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defalutSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelName = "Channel Name";
                NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);

                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(0, notificationBuilder.build());

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        } else if (remoteMessage.getNotification() != null) {
            final String messageBody = remoteMessage.getNotification().getBody();
            final String messageTitle = remoteMessage.getNotification().getTitle();
//            updateContent(messageBody,messageTitle);


            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(), messageTitle + "  " + messageBody, Toast.LENGTH_LONG).show();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(messageTitle).setMessage(messageBody).setNegativeButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog = alertDialog.create();
            dialog.show();

                }

            }, 0);


        }
    }

    private void updateContent(String body,String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(body).setMessage(title);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }

}