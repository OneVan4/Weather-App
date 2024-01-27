package com.example.mashe4kinapogodaotivana

import android.app.Application
import android.content.Intent
import androidx.room.Room
import com.example.mashe4kinapogodaotivana.Room.OpenWeatherDataBase
import com.example.mashe4kinapogodaotivana.view.adapters.SettingsHolder

const val appSettings = "App Settings"
const val isStartedUp="is started up"



class App:Application() {
    companion object {
        lateinit var db :OpenWeatherDataBase
    }
    override fun onCreate() {

        super.onCreate()
        db = Room.databaseBuilder(this,OpenWeatherDataBase::class.java,"OpenWeatherDB").fallbackToDestructiveMigration().build()

        val sharp = getSharedPreferences(appSettings, MODE_PRIVATE)

        SettingsHolder.OnCreate(sharp)

        val flag = sharp.contains(isStartedUp)
        if(!flag){
            val editor = sharp.edit()
            editor.putBoolean(isStartedUp,true).apply()
            val intent = Intent(this,Initial::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}