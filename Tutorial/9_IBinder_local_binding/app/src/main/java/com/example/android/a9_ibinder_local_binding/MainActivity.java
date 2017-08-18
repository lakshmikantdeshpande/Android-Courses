package com.example.android.a9_ibinder_local_binding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startService, stopService;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(this, MyService.class);
        startService = (Button) findViewById(R.id.btn_startService);
        stopService = (Button) findViewById(R.id.btn_stopService);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startService:
                startService(serviceIntent);
                break;
            case R.id.btn_stopService:
                stopService(serviceIntent);
                break;
        }
    }
}
