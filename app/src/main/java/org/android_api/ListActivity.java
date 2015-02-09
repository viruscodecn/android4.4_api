package org.android_api;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListActivity extends ActionBarActivity {

    public class MyDbHelper extends SQLiteOpenHelper{

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table test_user (name varchar(20))";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            // 每次成功打开数据库后首先被执行的方法
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initSaveNameButton();
        refreshListView();
    }

    private void initSaveNameButton(){
        Button btn=(Button)findViewById(R.id.btnSaveName);
        btn.setOnClickListener(btnSaveNameClick);
    }

    private Button.OnClickListener btnSaveNameClick =new Button.OnClickListener(){
        public void onClick(View v) {
            EditText tv = (EditText)findViewById(R.id.etxtName);
            String name = tv.getText().toString();
            Log.i("My","插入数据库:"+name);
            SQLiteDatabase db = getDb();
            ContentValues cv = new ContentValues();
            cv.put("name",name);
            db.insert("test_user",null,cv);
            db.close();
            refreshListView();
        }
    };

    private SQLiteDatabase getDb(){
        MyDbHelper dh = new MyDbHelper(ListActivity.this,"testDb",null,1);
        return dh.getWritableDatabase();
    }

    private void refreshListView(){
        SQLiteDatabase db = getDb();
        ListView listView = (ListView)findViewById(R.id.listView);
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        Cursor c = db.query("test_user", new String[]{"name"}, "", null, "", "", "", "");
        while(c.moveToNext()){
            Map<String,String> map = new HashMap<String,String>();
            map.put("name",c.getString(c.getColumnIndex("name")));
            data.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(ListActivity.this,data,R.layout.my_listtest,
                new String[]{"name"},
                new int[]{R.id.name});
        listView.setAdapter(sa);
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
