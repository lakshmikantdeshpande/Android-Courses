package com.example.android.a7_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonStart, buttonStop;
    private EditText editText;
    private int num = 0;
    private boolean mStopLoop;
    private MyAsyncTask myAsyncTask;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
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

/*                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mStopLoop) {
                            try {
                                Thread.sleep(1000);
//                          A)    We can't update the textbox from this thread
//                                editText.setText(String.valueOf(num));

//                          B)    Now we can
//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        editText.setText(String.valueOf(++num));
//                                    }
//                                });
//                           C)    Directly call method from view object
                                editText.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        editText.setText(String.valueOf(++num));
                                    }
                                });
                            } catch (InterruptedException ie) {
                            }
                        }
                    }
                }).start();*/
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(num);
                break;

            case R.id.btnStop:
//                mStopLoop = false;
                myAsyncTask.cancel(true);
                Log.i(TAG, "Loop stopped : " + Thread.currentThread().getId());
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        private int counter = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            counter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            counter = integers[0];
            while (mStopLoop) {
                try {
                    Thread.sleep(1000);
                    if (isCancelled()) {
                        break;
                    } else {
                        ++counter;
                        publishProgress(counter);
                    }
                } catch (InterruptedException ie) {
                    Log.i(TAG, ie.getMessage());
                }
            }
            return counter;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            editText.setText(String.valueOf(integer));
            num = integer;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            editText.setText(String.valueOf(values[0]));
        }
    }
}
