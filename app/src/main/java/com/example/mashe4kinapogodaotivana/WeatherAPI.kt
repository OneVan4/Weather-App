package com.example.mashe4kinapogodaotivana

import com.example.mashe4kinapogodaotivana.businessModel.WeatherDataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/onecall?")
    fun getWeatherForecast(
        @Query("lat")lat:String,
        @Query("lon")lon:String,
        @Query("exclude")exclude:String="minutely,alerts",
        @Query("appid")appid:String="1bd576fa117471852376bba0d8b15b20",
        @Query("lang")lang:String="en",
    ): Observable<WeatherDataModel>
}