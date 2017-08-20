package com.example.android.a13_ordered_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendOrderedBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("a.b.c.d");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//      Ordered broadcast will pass the intent through all the receivers in the order they are declared in the manifest
//      sendOrderedBroadcast(intent, null);


//      This intent will go through each broadcast receiver one by one, and return to the calling activity
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Intent returned to the caller", Toast.LENGTH_SHORT).show();
            }
        }, null, MainActivity.RESULT_OK, null, null);
    }
}
