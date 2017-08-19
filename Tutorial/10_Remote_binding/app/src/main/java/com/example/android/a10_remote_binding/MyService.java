package com.example.android.a10_remote_binding;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by s on 17/8/17.
 */

public class MyService extends Service {
    public static final int GET_RANDOM_NUMBER_FLAG = 0;
    private final static String TAG = MyService.class.getSimpleName();
    private final int MIN = 0;
    private final int MAX = 100;
    private int mRandomNumber;
    private boolean isRandomGeneratorOn;
    private IBinder mBinder = new MyServiceBinder();
    private Messenger randomNumberMessenger = new Messenger(new RandomNumberRequestHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Package Name: " + intent.getPackage());
        if (intent.getPackage().equals("com.example.android.a10_remote_binding_client_side")) {
            Toast.makeText(getApplicationContext(), "Correct Package", Toast.LENGTH_SHORT).show();
            return randomNumberMessenger.getBinder();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Package", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "In OnRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service started  " + String.valueOf(Thread.currentThread().getId()));
        isRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
//        stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.i(TAG, String.valueOf(Thread.currentThread().getId()));
    }

    private void startRandomNumberGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (isRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(TAG, Thread.currentThread().getId() + " Random Number : " + mRandomNumber);
                }
            } catch (InterruptedException ioe) {
                Log.i(TAG, "Thread interrupted");
            }
        }
    }

    private void stopRandomNumberGenerator() {
        isRandomGeneratorOn = false;
    }

    public int getRandomNumber() {
        return mRandomNumber;
    }

    private class RandomNumberRequestHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_RANDOM_NUMBER_FLAG:
                    Message messageSendRandomNumber = Message.obtain(null, GET_RANDOM_NUMBER_FLAG);
                    messageSendRandomNumber.arg1 = getRandomNumber();
                    try {
                        msg.replyTo.send(messageSendRandomNumber);
                    } catch (RemoteException re) {
                        Log.i(TAG, re.getMessage());
                    }
            }
        }
    }

    class MyServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
