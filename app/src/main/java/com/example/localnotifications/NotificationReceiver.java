package com.example.localnotifications;


import android.app.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NotificationReceiver extends BroadcastReceiver {

    public final String CHANNEL_ID = "1";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        Notification.Builder builder = new Notification.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentTitle("Title")
                .setContentText("Notification Text")
                .setPriority(Notification.PRIORITY_DEFAULT);

        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        compat.notify(1, builder.build());
    }



    //=======================CODE TO PUT IN CALLING CLASS FOR THIS CLASS================

//    Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 35);
//        calendar.set(Calendar.SECOND, 0);
//    Intent i = new Intent(getApplication(), NotificationReceiver.class);
//    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
//            100, i, PendingIntent.FLAG_IMMUTABLE);
//
//
//    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
}
