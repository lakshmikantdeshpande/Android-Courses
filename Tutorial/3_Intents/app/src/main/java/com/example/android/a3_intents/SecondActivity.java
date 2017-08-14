package com.example.android.a3_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.edit;

/**
 * Created by s on 15/8/17.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = this.getIntent();
        Toast.makeText(this, intent.getStringExtra("key"), Toast.LENGTH_SHORT).show();

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(intent.getSerializableExtra("person").toString());
    }
}
