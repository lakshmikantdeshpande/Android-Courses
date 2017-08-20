package com.example.android.a13_ordered_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by s on 19/8/17.
 */

public class MyBroadcastReceiver1 extends BroadcastReceiver {
    private final static String TAG = MyBroadcastReceiver1.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast1 received", Toast.LENGTH_SHORT).show();

        Bundle bundle = getResultExtras(true);
        String str = bundle.getString("key");
        Log.i(TAG, str + TAG);
        bundle.putString("key", str + ">" + TAG);
    }
}
