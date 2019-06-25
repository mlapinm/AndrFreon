package com.example.freon.a41base2;
//подцеплено контекстное меню
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    EditText mEditText;
    String[] strings = {"1","2","3"};
    ArrayAdapter<String> mStringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.myListView);
        mEditText = (EditText)findViewById(R.id.edit_text);
        mStringArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_view, strings);
        mListView.setAdapter(mStringArrayAdapter);

        registerForContextMenu(mListView);
    }

    public void buttonSave(View view) {
        String string = mEditText.getText().toString();
        Toast.makeText(this,"onclick  = " + string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        TextView textView = (TextView)info.targetView;
        CharSequence itemList = textView.getText();
        menu.setHeaderTitle(itemList);
        menu.add(0, Menu.FIRST, Menu.NONE, "item1");
        menu.add(0,Menu.FIRST+1, Menu.NONE, "item2");

    }
}