package com.example.mashe4kinapogodaotivana.presenters

import android.annotation.SuppressLint
import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.repos.mainRepos
import com.example.mashe4kinapogodaotivana.view.MainView

class MainPresenter:BasePresenter<MainView>() {
    private val repo = mainRepos(ApiProvider())


    override fun enable() {
       repo.dataEmitter.subscribe{response ->
           viewState.displayLocation(response.cityName)
           viewState.displayCurrentDate(response.weatherData)
           viewState.displayDailyData(response.weatherData.daily)
           viewState.displayHourlyData(response.weatherData.hourly)
           response.error?.let{viewState.displayError(response.error)}
       }
    }

    fun refresh(lat:String, lon:String){
        viewState.setLoading(true)
        repo.reloadData(lat,lon)
    }
}