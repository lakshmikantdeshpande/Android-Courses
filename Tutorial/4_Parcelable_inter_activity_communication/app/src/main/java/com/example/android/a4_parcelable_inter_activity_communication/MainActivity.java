package com.example.android.a4_parcelable_inter_activity_communication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takeAway(View view) {
        Person person = new Person();
        person.setName("Sinna");

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("person", person);
        startActivity(intent);
    }
}
