package com.example.android.a5_threading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonStart, buttonStop;
    private int num = 0;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.btnStart);
        buttonStop = (Button) findViewById(R.id.btnStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                mStopLoop = true;

//                 This hangs our application UI
//                while (mStopLoop) {
//                    Log.i(TAG, "Loop started : " + Thread.currentThread().getId());
//                }

                // This doesn't hang our UI
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mStopLoop) {
                            Log.i(TAG, "Loop started : (" + ++num + ")   " + Thread.currentThread().getId());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ie) {
                            }
                        }
                    }
                }).start();
                break;

            case R.id.btnStop:
                mStopLoop = false;
                Log.i(TAG, "Loop stopped : " + Thread.currentThread().getId());
                break;
        }
    }
}
