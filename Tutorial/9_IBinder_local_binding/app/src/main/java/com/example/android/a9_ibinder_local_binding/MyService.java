package com.example.android.a9_ibinder_local_binding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by s on 17/8/17.
 */

public class MyService extends Service {
    private final static String TAG = MyService.class.getSimpleName();
    private final int MIN = 0;
    private final int MAX = 100;
    private int mRandomNumber;
    private boolean isRandomGeneratorOn;
    private IBinder mBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "In OnBind");
        return mBinder;
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

    class MyServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
