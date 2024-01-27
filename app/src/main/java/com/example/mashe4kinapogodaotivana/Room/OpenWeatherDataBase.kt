package com.example.mashe4kinapogodaotivana.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherDataEntity::class,GeoCodeEntity::class], exportSchema = false, version = 1)
abstract class OpenWeatherDataBase:RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    abstract fun getGeoCodeDao():GeoCodeDao
}