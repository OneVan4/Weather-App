package com.example.mashe4kinapogodaotivana.Room

import androidx.room.*

@Dao
interface GeoCodeDao {
    @Query("Select * FROM GeoCode")
    fun getAll():List<GeoCodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item:GeoCodeEntity)

    @Delete
    fun remove(item:GeoCodeEntity)
}