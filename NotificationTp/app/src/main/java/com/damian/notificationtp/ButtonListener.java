package com.damian.notificationtp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Damian on 2/19/2017.
 */

public class ButtonListener extends BroadcastReceiver  {

    static NotificationMaker notificationMaker;

    private static boolean b=false;
    @Override
    public void onReceive(Context context, Intent intent) {
        //called whenever the receiver receives the intent
        //right now you want to consume the intent ie not create noti
        String source=intent.getAction();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.cancel(intent.getExtras().getInt("id"));//will consume intent
        Toast.makeText(context,source,Toast.LENGTH_SHORT).show();

        if(source.equals("button2Clicked"))
        {
            if(b)
                notificationMaker.setButtonText("random");
            else
                notificationMaker.setButtonText("world");
            ButtonListener.b=!b;
        }


    }
}
