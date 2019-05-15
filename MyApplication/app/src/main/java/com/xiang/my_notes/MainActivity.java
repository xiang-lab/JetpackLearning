package com.xiang.my_notes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    private SimpleCursorAdapter adapter = null;
    private NotesDB db;
    private SQLiteDatabase dbRead;
    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private static final int REQUEST_CODE_EDIT_NOTE = 2;

    private View.OnClickListener btnAddNote_clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(MainActivity.this, AtyEditNote.class), REQUEST_CODE_ADD_NOTE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new NotesDB(this);
        dbRead = db.getReadableDatabase();

        adapter = new SimpleCursorAdapter(this, R.layout.notes_list_cell, null,
                new String[]{NotesDB.COLUMN_NAME_NOTE_NAME,NotesDB.COLUMN_NAME_NOTE_DATE},
                new int[]{R.id.tvName, R.id.tvDate});
        setListAdapter(adapter);

        refreshNotesListView();

        findViewById(R.id.btnAddNote).setOnClickListener(btnAddNote_clickHandler);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Cursor c = adapter.getCursor();
        c.moveToPosition(position);

        Intent i = new Intent(MainActivity.this, AtyEditNote.class);
        i.putExtra(AtyEditNote.EXTRA_NOTE_ID, c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_ID)));
        i.putExtra(AtyEditNote.EXTRA_NOTE_NAME, c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_NAME)));
        i.putExtra(AtyEditNote.EXTRA_NOTE_CONTENT, c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_CONTENT)));

        startActivityForResult(i, REQUEST_CODE_EDIT_NOTE);


        super.onListItemClick(l, v, position, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_ADD_NOTE:
            case REQUEST_CODE_EDIT_NOTE:
                if (requestCode == Activity.RESULT_OK) {
                    refreshNotesListView();
                }
                break;
            default:
                break;
        }
    }

    public void refreshNotesListView() {

        adapter.changeCursor(dbRead.query(NotesDB.TABLE_NAME_NOTES, null, null, null, null, null, null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
