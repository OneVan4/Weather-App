package com.example.mashe4kinapogodaotivana

import android.app.Application
import android.content.Intent

const val appSettings = "App Settings"
const val isStartedUp="is started up"


class App:Application() {
    override fun onCreate() {
        super.onCreate()
        val sharp = getSharedPreferences(appSettings, MODE_PRIVATE)
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