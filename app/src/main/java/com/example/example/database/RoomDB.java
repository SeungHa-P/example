package com.example.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.internal.$Gson$Preconditions;

@Database(entities = {SubwayDto.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDB;
    public abstract SubwayDao subwayDao();

    public static RoomDB getAppDatabase(Context context){
        if(roomDB == null){
            roomDB = Room.databaseBuilder(context, RoomDB.class,"subwayr-db").allowMainThreadQueries().build();
        }
        return roomDB;
    }

public static void destroyInstance(){
        roomDB = null;
}

}
