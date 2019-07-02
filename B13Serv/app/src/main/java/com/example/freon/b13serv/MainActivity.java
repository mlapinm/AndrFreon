package com.example.freon.b13serv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.editText);
        mEditText.setText("123");

        Intent intent = new Intent(MainActivity.this, MyCoolService.class);
        String string = mEditText.getText().toString();
        intent.putExtra(MyCoolService.KEY_TEXT,string);
        startService(intent);

    }

    public void onClick(View view) {
        Log.d("happy", "onClick");
    }
}
