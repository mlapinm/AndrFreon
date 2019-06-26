package com.example.freon.a44base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String[] FIELDS =
            {"_id", DbOpenHelper.COLUMN_NOTE, };
    private static final String[] FROM =
            {DbOpenHelper.COLUMN_NOTE,};
    private static final int[] TO = {R.id.noteView, };
    private static final java.lang.String ORDER = "_id DESC";
    EditText mInputField;
    ListView mNotesList;

    SimpleCursorAdapter mAdapter;

    DbOpenHelper mHelper = new DbOpenHelper(this);
    SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = (EditText)findViewById(R.id.inputField);
        mNotesList = (ListView)findViewById(R.id.notesList);
        mAdapter = new SimpleCursorAdapter(this,
                R.layout.note, null, FROM, TO, 0 );
        mNotesList.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDb = mHelper.getWritableDatabase();
        Cursor c = mDb.query(DbOpenHelper.DB_TABLE, FIELDS,
                null, null, null, null, ORDER);
        mAdapter.swapCursor(c);
    }

    public void onOkButtonClick(View view) {
    }
}
