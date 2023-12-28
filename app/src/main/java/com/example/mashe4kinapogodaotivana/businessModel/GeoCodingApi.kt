package com.example.mashe4kinapogodaotivana.businessModel

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {
    @GET("geo/1.0/reverse?")
    fun getCityByCoordinates(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("limit")limit:String="10",
        @Query("appid")appid:String="1bd576fa117471852376bba0d8b15b20"
    ):Observable<List<GeoCodeModel>>
}