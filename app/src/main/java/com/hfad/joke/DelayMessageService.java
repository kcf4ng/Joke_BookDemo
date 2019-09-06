package com.hfad.joke;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class DelayMessageService extends IntentService {

    private static final String TAG = "DelayMessageService";
    public static  final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 5432;



    public DelayMessageService() {
        super( "DelayMessageService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //執行的程式碼
        synchronized (this)
        {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String Text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(Text);
    }

    private void showText(String text) {
    Log.v(TAG,"The message is : "+text);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }



        //建立通知組建式
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(text)
                .setChannelId("my_channel_01")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{0,1000})
                .setAutoCancel(true);
        //建立action
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

      builder.setContentIntent(actionPendingIntent);

      //發出通知

        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }


}
