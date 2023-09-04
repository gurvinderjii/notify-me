package com.codezlab.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 100;
    private static final String CHANNEL_ID = "Message Channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notify = findViewById(R.id.notify_me);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.notify,null);
        BitmapDrawable bm = (BitmapDrawable) drawable;
        Bitmap largeIcon = bm.getBitmap();

        

        Notification newMsg;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newMsg = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notify)
                    .setContentText("Test Message")
                    .setSubText("You have some new notifications")
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Messages",NotificationManager.IMPORTANCE_HIGH));
        }else{
            newMsg = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notify)
                    .setContentText("Test Message")
                    .setSubText("You have some new notifications")
                    .build();
        }


//        nm.notify(NOTIFICATION_ID, newMsg);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm.notify(NOTIFICATION_ID,newMsg);
            }
        });
    }
}