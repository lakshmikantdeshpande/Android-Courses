package com.example.android.a4_parcelable_inter_activity_communication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        populateTextView();
    }

    private void populateTextView() {
        Person person = getIntent().getParcelableExtra("person");
        ((TextView) findViewById(R.id.textView)).setText(person.getName());
    }
}
