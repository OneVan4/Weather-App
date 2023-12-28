package com.example.mashe4kinapogodaotivana

import com.example.mashe4kinapogodaotivana.businessModel.GeoCodingApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiProvider {
    private val openWeatherMap :Retrofit by lazy { initApi() }

    private fun initApi() =Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pro.openweathermap.org/")
        .build()
    fun provideWeatherAPI(): WeatherAPI = openWeatherMap.create(WeatherAPI::class.java)
    fun provideGeoCodeAPI():GeoCodingApi = openWeatherMap.create(GeoCodingApi::class.java)
}