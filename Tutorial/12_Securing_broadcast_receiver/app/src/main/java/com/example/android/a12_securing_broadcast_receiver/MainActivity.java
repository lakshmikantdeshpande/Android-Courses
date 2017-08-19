package com.example.android.a12_securing_broadcast_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendLocalBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("a.b.c.d");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intent);
    }
}
