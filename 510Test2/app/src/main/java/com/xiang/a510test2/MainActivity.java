package com.xiang.a510test2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    private Button addNote;
    private TextView tv_content;
    private NotesDB db;
    private SQLiteDatabase dbread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        tv_content = (TextView) findViewById(R.id.tv_content);
        listView = (ListView) findViewById(R.id.listview);
        dataList = new ArrayList<>();

        addNote = (Button) findViewById(R.id.btn_editnote);
        mContext = this;
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteEdit.ENTER_START = 0;
                Intent intent = new Intent(mContext, noteEdit.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

        db = new NotesDB(this);
        dbread = db.getReadableDatabase();
        // 清空数据库中表的内容
        // dbread.execSQL("delete from note")
        RefreshNotesList();

        listView.setOnItemClickListener(this);

    }

    // 清楚掉掉list中的数据，然后再重新导入数据
    public void RefreshNotesList() {
        int size = dataList.size();
        if (size > 0) {
            dataList.removeAll(dataList);
            simp_adapter.notifyDataSetChanged();
            listView.setAdapter(simp_adapter);
        }

        simp_adapter = new SimpleAdapter(this, getData(), R.layout.listview,
                new String[]{"tv_content", "tv_date"},
                new int[]{R.id.tv_content, R.id.tv_date});
        listView.setAdapter(simp_adapter);
    }

    private List<Map<String, Object>> getData() {
        Cursor cursor = dbread.query("note", null, "content!=\"\"",
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Map<String, Object> map = new HashMap<>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            dataList.add(map);
        }
        cursor.close();
        return dataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            RefreshNotesList();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        noteEdit.ENTER_START = 1;
//        Log.d("position", position + "");
//        TextView
//        content=(TextView)listview.getChildAt(position).findViewById(R.id.tv_content);
//        String content1 = content.toString();

        String content = listView.getItemAtPosition(position) + "";
        String content1 = content.substring(content.indexOf("=") + 1,
                content.indexOf(","));
        Log.d("CONTENT", content1);
        Cursor c = dbread.query("note", null,
                "content=" + "'" + content1 + "'", null, null, null, null);
        while (c.moveToNext()) {
            String No = c.getString(c.getColumnIndex("_id"));
            Log.d("TEXT", No);
            // Intent intent = new Intent(mContext, noteEdit.class);
            // intent.putExtra("data", text);
            // setResult(4, intent);
            // // intent.putExtra("data",text);
            // startActivityForResult(intent, 3)
            Intent myIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("info", content1);
            noteEdit.id = Integer.parseInt(No);
            myIntent.putExtras(bundle);
            myIntent.setClass(MainActivity.this, noteEdit.class);
            startActivityForResult(myIntent, 1);
        }
    }

}

















