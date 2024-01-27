package com.example.mashe4kinapogodaotivana.Room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.example.mashe4kinapogodaotivana.businessModel.LocalNames

@Entity(tableName = "GeoCode", primaryKeys = ["lat","lon"])
class GeoCodeEntity (
    @ColumnInfo(name = "name") val name:String,
    @Embedded val localNames: LocalNames,
    @ColumnInfo(name = "lat") val lat:Double,
    @ColumnInfo(name = "lon") val lon:Double,
    @ColumnInfo(name = "country") val country:String,
    @ColumnInfo(name = "state") val state:String,
    @ColumnInfo(name = "isFavorite") val isFavorite:String,
        )