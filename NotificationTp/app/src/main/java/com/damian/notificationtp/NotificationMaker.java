package com.damian.notificationtp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Damian on 2/19/2017.
 */

public class NotificationMaker {
    private Context context;
    private Notification.Builder builder;
    private NotificationManager notificationManager;

    private RemoteViews remoteViews;
    private int notificationId;
    public NotificationMaker(Context context)
    {

        this.context=context;
        initRemoteView();
        initBuilder();
        ButtonListener.notificationMaker=this;
    }

    private void initRemoteView(){
        this.notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        this.remoteViews=new RemoteViews(context.getPackageName(),R.layout.remote_view);
        this.remoteViews.setTextViewText(R.id.text,"hello randomranomdworld world");

        //to set click listener of remote view you require broadcast receiver which are kinda like servcices and run in the background
        this.notificationId=(int)1;
        Intent button1Intent=new Intent("button1Clicked"),button2Intent=new Intent("button2Clicked"),button3Intent=new Intent("button3Clicked");
        button1Intent.putExtra("id",this.notificationId);

        //to invoke broadcasts you require pending intents which allow you to send and receive intents on the bg thread


        PendingIntent b1Intent=PendingIntent.getBroadcast(context,123,button1Intent,0);//prolly will need 3 intents to separate 3buttons ka intent ka respective actions
        PendingIntent b2Intent=PendingIntent.getBroadcast(context,456,button2Intent,0),b3Intent=PendingIntent.getBroadcast(context,678,button3Intent,0);


        //now bind this to the remoteview so that the desired broadcast receiver is triggered
        this.remoteViews.setOnClickPendingIntent(R.id.b1,b1Intent);
        this.remoteViews.setOnClickPendingIntent(R.id.b2,b2Intent);
        this.remoteViews.setOnClickPendingIntent(R.id.b3,b3Intent);
    }
    @TargetApi(24)
    private void initBuilder(){




        //now that this is done create a receiver in manifest which listent to a certain intent named buttonClicked for
        //that open receiver tag set name with name tag..NOTE NAME MUST BE A CLASS NAME THAT YOUVE DEFINED WITHIN THE PROJECT
        //then add intentfilter with intentfilter tag and
        //define action tag with name as buttonCliked or any suhc intent name



        //creating noti

        //the below intent is for whenever the user clicks on something other than the things that were provided to him on the screen

        Intent mainActivityIntent=new Intent(context,MainActivity.class);
        PendingIntent getMainActivityPendingIntent=PendingIntent.getActivity(context,0,mainActivityIntent,0);
        this.builder= new Notification.Builder(context);
        this.builder.setSmallIcon(R.mipmap.ic_launcher).setContentIntent(getMainActivityPendingIntent);
        if(Build.VERSION.SDK_INT< android.os.Build.VERSION_CODES.M)
            this.builder.setContent(this.remoteViews);
        else
            this.builder.setCustomContentView(this.remoteViews);



    }

    public void makeNotification(){
        notificationManager.notify(this.notificationId,this.builder.build());
    }
    public void setText(String s){
        this.remoteViews.setTextViewText(R.id.text,s);
        this.makeNotification();
    }
    public void setButtonText(String s){
        this.remoteViews.setTextViewText(R.id.b2,s);
        this.makeNotification();
    }

    public void closeNotification(){
        this.notificationManager.cancel(this.notificationId);
    }
}
