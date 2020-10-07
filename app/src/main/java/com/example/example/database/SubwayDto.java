package com.example.example.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subwayr")
public class SubwayDto {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int index;

    private  String station;
    @ColumnInfo(name = "address")
    private  String address;

    @NonNull
    public String getStation() {
        return station;
    }

    public void setStation(@NonNull String station) {
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
