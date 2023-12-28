package com.example.mashe4kinapogodaotivana.repos

import android.annotation.SuppressLint
import android.util.Log
import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.businessModel.WeatherDataModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class mainRepos(apiProvider: ApiProvider):BaseRepos<mainRepos.serverResponse>(apiProvider) {
    @SuppressLint("CheckResult")
    fun reloadData(lat:String, lon:String){
        Observable.zip(
            api.provideWeatherAPI().getWeatherForecast(lat, lon),
            api.provideGeoCodeAPI().getCityByCoordinates(lat,lon).map {
                it.asSequence().map { model->model.name }.toList().filterNotNull().first()
            },
            {weatherData,GeoCode -> serverResponse(GeoCode,weatherData)}
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({dataEmitter.onNext(it)},{ Log.d("fe","FE")})
    }

    data class serverResponse(val cityName:String, val weatherData: WeatherDataModel,val error:Throwable?=null)
}