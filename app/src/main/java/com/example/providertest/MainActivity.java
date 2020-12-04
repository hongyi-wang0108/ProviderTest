package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
    private Button button_insert;
    private Button button_delete;
    private Button button_update;
    private Button button_query;
    //private MyContentProvider myContentProvider;
    //private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String newid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.newdatabasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("author","ha");
                values.put("price",1.1);
                values.put("pages",1);
                values.put("name","haha");
                //myContentProvider.insert(uri,values); 思路错了
                Uri newuri = getContentResolver().insert(uri, values);
                newid = newuri.getPathSegments().get(1);//?
            }
        });
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriqu = Uri.parse("content://com.example.newdatabasetest.provider/book");
                Cursor cursor = getContentResolver().query(uriqu, null, null, null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        String author = "author:"
                                + cursor.getString(cursor.getColumnIndex("author"))+"\n"
                                +"price:"
                                + cursor.getString(cursor.getColumnIndex("price"))+"\n"
                                +"pages:"
                                + cursor.getString(cursor.getColumnIndex("pages"))+"\n"
                                +"name:"
                                + cursor.getString(cursor.getColumnIndex("name"))+"\n";
                        Log.d("niu", author);
                    }
                }
                cursor.close();
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriup = Uri.parse("content://com.example.newdatabasetest.provider/book/"+newid);
                ContentValues valuesup = new ContentValues();
                valuesup.put("author"," haha");
                valuesup.put("price",1.2);
                valuesup.put("pages",2);
                valuesup.put("name","hahahaha");
                getContentResolver().update(uriup, valuesup, null, null);

            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uridel = Uri.parse("content://com.example.newdatabasetest.provider/book/"+newid);
                getContentResolver().delete(uridel,null,null);

            }
        });
    }

    private void init() {
        button_insert = findViewById(R.id.button_insert);
        button_query = findViewById(R.id.button_query);
        button_update = findViewById(R.id.button_update);
        button_delete = findViewById(R.id.button_delete);
    }


}