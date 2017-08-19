package com.example.android.a9_ibinder_local_binding;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private Button startService, stopService, btnBindService, btnUnBindService, getRandomNumber;
    private Intent serviceIntent;
    private TextView textView;

    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(this, MyService.class);
        startService = (Button) findViewById(R.id.btn_startService);
        stopService = (Button) findViewById(R.id.btn_stopService);
        btnBindService = (Button) findViewById(R.id.btn_bindService);
        btnUnBindService = (Button) findViewById(R.id.btn_unbindService);
        getRandomNumber = (Button) findViewById(R.id.btn_random);

        textView = (TextView) findViewById(R.id.counterTextView);
        myService = new MyService();

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        getRandomNumber.setOnClickListener(this);
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
            case R.id.btn_bindService:
                bindService();
                break;
            case R.id.btn_unbindService:
                unbindService();
                break;
            case R.id.btn_random:
                setRandomNumber();
                break;
        }
    }

    private void bindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
        Log.i(TAG, "Bound to the service");
        textView.setText("Bound to the service");
    }

    private void unbindService() {
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
            Log.i(TAG, "Service is unbound");
        } else {
            Log.i(TAG, "Service is not Bound");
            textView.setText("Service is not Bound");
        }

    }

    private void setRandomNumber() {
        Log.i(TAG, "In setRandomNumber");
        if (isServiceBound) {
            textView.setText("Random Number is " + myService.getRandomNumber());
        } else {
            textView.setText("Service is not bound");
        }
    }
}
