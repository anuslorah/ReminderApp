package com.murach.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anusl on 6/5/2017.
 */

public class ReminderService extends Service {

    Timer timer;

    @Override
    public void onCreate(){
        Log.d("Reminder service", "Service created");
        startTimer();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("Reminder service", "Service started");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent){
        Log.d("Reminder service", "Service bound");
        return null;
    }

    @Override
    public void onDestroy(){
        Log.d("Reminder service", "Service destroyed");
        stopSelf();
        stopTimer();
    }

    private void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d("Reminder service", "Look into the distance. It’s good for your eyes!");

                sendNotification("Look into the distance. It’s good for your eyes!");

            }
        };
        timer = new Timer(true);
        int delay = 1000* 5; //5 seconds
        int interval = 1000*10*60; //60 minutes
        timer.schedule(task, delay,interval);
    }

    private void stopTimer(){
        if ((timer != null)){
            timer.cancel();
        }
    }

    private void sendNotification(String text) {
        // create the intent for the notification
        Intent notificationIntent = new Intent(this, ReminderActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // create the pending intent
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, flags);

        // create the variables for the notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "Take care of yourself!";
        CharSequence contentTitle = getText(R.string.app_name);
        CharSequence contentText = text;

        // create the notification and set its data
        Notification notification =
                new Notification.Builder(this)
                        .setSmallIcon(icon)
                        .setTicker(tickerText)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

        // display the notification
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        final int NOTIFICATION_ID = 1;
        manager.notify(NOTIFICATION_ID, notification);
    }
}
