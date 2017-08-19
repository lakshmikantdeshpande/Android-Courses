package com.example.android.a12_securing_broadcast_receiver_tester;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    // This ensures that the broadcast receiver is not propogated outside the app
    private LocalBroadcastManager lbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lbm = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    public void sendGlobalBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("a.b.c.d");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        /*
            Normal way of sending the broadcasts
            sendBroadcast(intent);

            The following is the secure way of sending the broadcasts
        */
        lbm.sendBroadcast(intent);

    }
}
