package com.damian.notificationtp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {


    private Context context;
    private NotificationMaker notificationMaker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context=this;

        notificationMaker=new NotificationMaker(context);





    }
    @Override
    public void onPause(){
        super.onPause();
        notificationMaker.makeNotification();
    }
    @Override
    public void onResume(){
        super.onResume();
        notificationMaker.closeNotification();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        notificationMaker.closeNotification();

    }



}
