package com.example.freon.a86clstorefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String mFileName;
    Store mStore;
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFileName = "file1.txt";
        mStore = new Store(this, mFileName);
        String string = mStore.getString();
        mEditText = (EditText)findViewById(R.id.editText);
        mEditText.setText(string);
        //mEditText.setText("123");
    }

    @Override
    protected void onStop() {
        super.onStop();
        String string = mEditText.getText().toString();
        mStore.storeString(string);
    }
}

