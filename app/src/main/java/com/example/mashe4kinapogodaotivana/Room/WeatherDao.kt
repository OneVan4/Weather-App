package com.example.mashe4kinapogodaotivana.Room

import androidx.room.*


@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherData WHERE id=1")
    fun getWeatherData():WeatherDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(data:WeatherDataEntity)

    @Delete
    fun deleteWeatherData(data:WeatherDataEntity)

    @Update
    fun updateWeatherData(data:WeatherDataEntity)
}