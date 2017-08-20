package com.example.android.a14_cursor_loader;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private TextView textView, idTextView, nameTextView;


    private String[] columnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };

    private String selectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";

    private String[] selectionArguments = new String[]{"Samuel"};

    private String orderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    // custom variable to check if the data is already loaded, if it is, it will cost less
    private boolean isDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        idTextView = (TextView) findViewById(R.id.id);
        nameTextView = (TextView) findViewById(R.id.name);
        loadData();
    }

    public void loadData() {
        // giving a unique ID to this loader
        // Don't reload the data again if the data is already loaded
        if (isDataLoaded) {
            getLoaderManager().restartLoader(1, null, this);
        } else {
            getLoaderManager().initLoader(1, null, this);
            isDataLoaded = true;
        }
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, columnProjection, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        Cursor cursor = (Cursor) o;
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

    @Override
    public void onLoaderReset(Loader loader) {
    }

    public void insert(View view) {

    }

    public void update(View view) {
    }

    public void delete(View view) {
    }
}
