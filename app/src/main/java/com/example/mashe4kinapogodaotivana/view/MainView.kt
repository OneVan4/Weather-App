package com.example.mashe4kinapogodaotivana.view

import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel
import com.example.mashe4kinapogodaotivana.businessModel.HourlyWeatherModel
import com.example.mashe4kinapogodaotivana.businessModel.WeatherDataModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView:MvpView {

    @AddToEndSingle
    fun displayLocation (data:String)
    @AddToEndSingle
    fun displayCurrentDate(data: WeatherDataModel)
    @AddToEndSingle
    fun displayHourlyData(data:List<HourlyWeatherModel>)
    @AddToEndSingle
    fun displayDailyData(data:List<DailyWeatherModel>)
    @AddToEndSingle
    fun displayError(error:Throwable)
    @AddToEndSingle
    fun setLoading (flag:Boolean)
}