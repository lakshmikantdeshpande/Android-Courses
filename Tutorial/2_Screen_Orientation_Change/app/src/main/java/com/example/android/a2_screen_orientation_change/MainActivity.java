package com.example.android.a2_screen_orientation_change;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
//    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "in method onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "in method onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "in method onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "in method onPause");
    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.i(TAG, "in method onSaveInstanceState");
//        outState.putString("samplekey", editText.getText().toString());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.i(TAG, "in method onRestoreInstanceState");
//        editText.setText(savedInstanceState.getString("samplekey"));
//    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "in method onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "in method onDestroy");
    }
}
