package com.example.memoapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
    static final  private String DBNAME = "memodata.splite";
    static  final private int VERSION = 1;

    public Helper(Context context){
        super(context, DBNAME,null, VERSION);

    }


    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE datas (date TEXT, text TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v){
        db.execSQL("DROP TABLE IF EXISTS datas");
        onCreate(db);
    }

}
