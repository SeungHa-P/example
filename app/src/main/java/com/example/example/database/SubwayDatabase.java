package com.example.example.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SubwayDatabase extends SQLiteOpenHelper {


    private static SubwayDatabase subwayDatabase;
    public static synchronized SubwayDatabase getInstance(Context context){
        if(subwayDatabase == null){
            subwayDatabase = new SubwayDatabase(context,"Subway",null,1);
        }
        return subwayDatabase;

    }


    public static final int DB_VERSION  = 1;
    public static final String DB_NAME = "Subway.db";
    public static final String TABLE_NAME = "subway";
    public static final String STATION = "station";
    public static final String ADDRESS = "address";



    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    STATION + " TEXT," +
                    ADDRESS + " TEXT" +");";


    private SubwayDatabase(@Nullable Context context,@Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("DBCREATE","디비 생성");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
