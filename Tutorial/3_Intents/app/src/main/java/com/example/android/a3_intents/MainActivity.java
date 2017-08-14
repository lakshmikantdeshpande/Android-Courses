package com.example.android.a3_intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button intentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentButton = (Button) findViewById(R.id.intentButton);
        intentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Switching to the next Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", "Value");
                startActivity(intent);


//                Implicit Intent Example
//                Intent intent = new Intent();
//                intent.setAction("com.sample.intent");
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                startActivity(intent);
            }
        });
    }
}
