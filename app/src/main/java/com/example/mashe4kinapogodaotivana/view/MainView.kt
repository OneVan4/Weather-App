package com.example.mashe4kinapogodaotivana.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView:MvpView {

    @AddToEndSingle
    fun displayLocation (data:String)
    @AddToEndSingle
    fun displayCurrentDate(data: WeatherData)
    @AddToEndSingle
    fun displayHourlyData(data:List<HourlyWeatherModel>)
    @AddToEndSingle
    fun displayDailyData(data:List<DailyWeatherModel>)
    @AddToEndSingle
    fun displayError(error:Throwable)
    @AddToEndSingle
    fun setLoading (flag:Boolean)
}