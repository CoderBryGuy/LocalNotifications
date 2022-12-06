package com.example.localnotifications;

import android.app.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    class TagsContract {
        final static String TOAST = "toast";
    }

    Button button;
    public final String CHANNEL_ID = "1";
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                button.setText("" + counter);

                if (counter % 5 == 0) {
                    startNotification();
                }

            }
        });
    }

    public void startNotification() {

        //=========toast intent============
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE);

        Intent actionIntent = new Intent(this, Receivers.class);
        actionIntent.putExtra(TagsContract.TOAST, "This is a notification message");
        PendingIntent actionPending = PendingIntent.getBroadcast(this, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification.Action action = new Notification.Action.Builder(
                Icon.createWithResource(this, R.drawable.ic_baseline_add_alert_24), "Toast Message"
                , actionPending).build();


        //==========dismiss intent=========
        Intent dimissIntent = new Intent(this, ReceiverDismiss.class);
        PendingIntent dismissPending = PendingIntent.getBroadcast(this, 0, dimissIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification.Action dismissAction =
                new Notification.Action.Builder(Icon.createWithResource(
                        this, R.drawable.ic_baseline_add_alert_24),
                        "Dismiss", dismissPending).build();


        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.androidpng_241468);
        Bitmap icon_thumb = BitmapFactory.decodeResource(getResources(), R.drawable.terminator_android);
        String text = getResources().getString(R.string.big_text);

        Notification.Builder builder = new Notification.Builder(MainActivity.this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentTitle("Title")
                .setContentText("Notification Text")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(action)
                .addAction(dismissAction)
                .setColor(Color.RED)
                .setLargeIcon(icon_thumb)
                .setStyle(new Notification.BigPictureStyle().bigPicture(icon))
                .setStyle(new Notification.BigTextStyle().bigText(text));

        NotificationManagerCompat compat = NotificationManagerCompat.from(MainActivity.this);
        compat.notify(1, builder.build());
    }

}