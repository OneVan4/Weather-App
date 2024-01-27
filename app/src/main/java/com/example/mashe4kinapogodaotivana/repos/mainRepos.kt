package com.example.mashe4kinapogodaotivana.repos

import android.annotation.SuppressLint
import android.util.Log
import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.Room.WeatherDataEntity
import com.example.mashe4kinapogodaotivana.businessModel.WeatherDataModel
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class mainRepos(apiProvider: ApiProvider):BaseRepos<mainRepos.serverResponse>(apiProvider) {
    private val gson = Gson()
    private val dbAccess = db.getWeatherDao()
    @SuppressLint("CheckResult")
    fun reloadData(lat:String, lon:String){
        Observable.zip(
            api.provideWeatherAPI().getWeatherForecast(lat, lon),
            api.provideGeoCodeAPI().getCityByCoordinates(lat,lon).map {
                it.asSequence().map { model->model.name }.toList().filterNotNull().first()
            },
            {weatherData,GeoCode -> serverResponse(GeoCode,weatherData)}
        ).subscribeOn(Schedulers.io())
            .doOnNext { dbAccess.insertWeatherData(WeatherDataEntity(data = gson.toJson(it.weatherData), city = it.cityName)) }
            .onErrorResumeNext { Observable.just(serverResponse(dbAccess.getWeatherData().city,gson.fromJson(dbAccess.getWeatherData().data,WeatherDataModel::class.java),it)) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({dataEmitter.onNext(it)},{ Log.d("fe","FE")})
    }

    data class serverResponse(val cityName:String, val weatherData: WeatherDataModel,val error:Throwable?=null)
}