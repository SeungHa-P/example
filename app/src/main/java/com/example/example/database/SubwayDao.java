package com.example.example.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.example.Manager.Subway;

import java.util.List;

@Dao
public interface SubwayDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insert(SubwayDto subwayDto);

    @Query("SELECT station FROM subwayr")
    List<String> getStation();

    @Query("SELECT address FROM subwayr WHERE station = :st")
    String getAddress(String st);


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(SubwayDto subwayDto);


}
