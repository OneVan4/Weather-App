package com.example.mashe4kinapogodaotivana.businessModel

data class WeatherDataModel(
    val current: Current,
    val daily: List<DailyWeatherModel>,
    val hourly: List<HourlyWeatherModel>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int
)