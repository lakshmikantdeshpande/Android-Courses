package com.example.android.a8_services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private Button startService, stopService;
    private TextView textView;
    private Intent service;
    private boolean mstopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, String.valueOf(Thread.currentThread().getId()));

        startService = (Button) findViewById(R.id.btn_startService);
        stopService = (Button) findViewById(R.id.btn_stopService);
        textView = (TextView) findViewById(R.id.counterTextView);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        service = new Intent(MainActivity.this, MyService.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startService:
                mstopLoop = true;
                // starting service
                startService(service);
                break;
            case R.id.btn_stopService:
                stopService(service);
                break;
        }
    }
}
