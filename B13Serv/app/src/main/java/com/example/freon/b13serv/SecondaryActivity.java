package com.example.freon.b13serv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        mTextView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String string = intent.getStringExtra(MyCoolService.KEY_TEXT);
        mTextView.setText(string);
    }
}
