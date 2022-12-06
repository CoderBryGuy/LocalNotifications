package com.example.localnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receivers extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(MainActivity.TagsContract.TOAST);
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
