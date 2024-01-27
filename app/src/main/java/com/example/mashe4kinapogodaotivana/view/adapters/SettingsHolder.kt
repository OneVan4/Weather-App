package com.example.mashe4kinapogodaotivana.view.adapters

import android.content.SharedPreferences
import androidx.annotation.IdRes
import com.example.mashe4kinapogodaotivana.R
import java.util.InputMismatchException
import kotlin.math.roundToInt

const val TEMP ="Temp"
const val WIND_SPEED ="Wind speed"
const val PRESSURE = "Pressure"

object SettingsHolder {
    private lateinit var sharPref :SharedPreferences
    var temp = Settings.TEMP_CELSIUS
    var windSpeed =Settings.WIND_SPEED_MS
    var pressure = Settings.PRESSURE_HPA

    fun OnCreate(sharP:SharedPreferences){
        sharPref=sharP
        temp = getSetting(sharPref.getInt(TEMP,C))
        windSpeed = getSetting(sharP.getInt(WIND_SPEED,MS))
        pressure = getSetting(sharP.getInt(PRESSURE,HPA))
    }

    fun onDestroy(){
        val editor = sharPref.edit()
        editor.putInt(TEMP, temp.prefConst)
        editor.putInt(WIND_SPEED, windSpeed.prefConst)
        editor.putInt(PRESSURE, pressure.prefConst)
        editor.apply()
    }

    private fun getSetting(@IdRes id:Int) = when(id){
        C -> Settings.TEMP_CELSIUS
        F -> Settings.TEMP_FAHRENHEIT
        MS -> Settings.WIND_SPEED_MS
        KMH -> Settings.WIND_SPEED_KMH
        MM_HG -> Settings.PRESSURE_MMHG
        HPA -> Settings.PRESSURE_HPA
        else -> throw InputMismatchException()
    }

    const val C = 1;
    const val F = 2;
    const val MS = 3;
    const val KMH= 4;
    const val MM_HG = 5;
    const val HPA =6;

    enum class Settings(@IdRes val checkedViewId:Int, @IdRes val mesureUnitStringRes:Int, val prefConst:Int){
        TEMP_FAHRENHEIT(R.id.degreeF,R.string.f,F){
            override fun getValue(initValue:Double)= valueToString {(initValue - 273.15) * (9/5) + 32}
        },
        TEMP_CELSIUS(R.id.degreeC,R.string.c,C){
            override fun getValue(initValue:Double)= valueToString {(initValue - 273.15)}
        },
        WIND_SPEED_MS(R.id.metrPerSecond,R.string.ms, MS){
            override fun getValue(initValue:Double)= valueToString {initValue}
        },
        WIND_SPEED_KMH(R.id.kmPerHour,R.string.kmh, KMH){
            override fun getValue(initValue:Double)= valueToString {initValue * 3.6}
        },

        PRESSURE_MMHG(R.id.mmHG,R.string.mmHG, MM_HG){
            override fun getValue(initValue:Double)= valueToString {initValue /1.33222}
        },

        PRESSURE_HPA(R.id.hPa,R.string.hPa, HPA){
            override fun getValue(initValue: Double) = valueToString{initValue}

        };

        abstract fun getValue(initValue: Double):String
        protected fun valueToString(formula:()-> Double) =formula().roundToInt().toString()
    }
}