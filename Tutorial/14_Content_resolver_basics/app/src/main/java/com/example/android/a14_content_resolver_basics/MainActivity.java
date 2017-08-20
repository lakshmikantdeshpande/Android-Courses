package com.example.android.a14_content_resolver_basics;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);


        String[] columnProjection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };

        String selectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";

        String[] selectionArguments = new String[]{"Samuel"};

        String orderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


        // Should be run in a separate thread or CursorLoader
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                columnProjection,
                null, // selectionClause,
                null, // selectionArguments,
                orderBy);

        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            while (cursor.moveToNext()) {
                stringBuilder.append(cursor.getString(0) + " " + cursor.getString(1) + "\n");
            }
            textView.setText(stringBuilder.toString());
        } else {
            textView.setText("No contacts found!");
        }
    }
}
