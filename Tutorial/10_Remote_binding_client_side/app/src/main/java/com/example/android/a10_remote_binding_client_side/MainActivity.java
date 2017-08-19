package com.example.android.a10_remote_binding_client_side;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GET_RANDOM_NUMBER_FLAG = 0;
    private Intent serviceIntent;
    private TextView textView;
    private Button bindService, unbindService, randomNumber;
    private Messenger randomNumberRequestMessenger, randomNumberReceiveMessenger;
    private boolean isBound;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            randomNumberRequestMessenger = new Messenger(iBinder);
            randomNumberReceiveMessenger = new Messenger(new ReceiveRandomNumberHandler());
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            randomNumberReceiveMessenger = null;
            randomNumberRequestMessenger = null;
            isBound = false;
        }
    };
    private int generatedRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        bindService = (Button) findViewById(R.id.btn_bindService);
        unbindService = (Button) findViewById(R.id.btn_unbindService);
        randomNumber = (Button) findViewById(R.id.btn_random);

        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        randomNumber.setOnClickListener(this);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.android.a10_remote_binding", "com.example.android.a10_remote_binding.MyService"));
        serviceIntent.setPackage(getPackageName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bindService:
                bindToRemoteService();
                break;
            case R.id.btn_unbindService:
                unbindRemoteService();
                break;
            case R.id.btn_random:
                fetchRandomNumber();
                break;
        }

    }

    private void fetchRandomNumber() {
        if (isBound) {
            Message requestMessage = Message.obtain(null, GET_RANDOM_NUMBER_FLAG);
            requestMessage.replyTo = randomNumberReceiveMessenger;

            try {
                randomNumberRequestMessenger.send(requestMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Service is not Bound", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindToRemoteService() {
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
        Toast.makeText(getApplicationContext(), "Service Bound!", Toast.LENGTH_SHORT).show();
    }

    private void unbindRemoteService() {
        unbindService(serviceConnection);
        isBound = false;
        Toast.makeText(getApplicationContext(), "Service Unbound!", Toast.LENGTH_SHORT).show();
    }

    class ReceiveRandomNumberHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            generatedRandomNumber = 0;
            switch (msg.what) {
                case GET_RANDOM_NUMBER_FLAG:
                    generatedRandomNumber = msg.arg1;
                    textView.setText("Random Number: " + generatedRandomNumber);
                    break;
            }
        }
    }


}
