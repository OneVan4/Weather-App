package com.example.mashe4kinapogodaotivana.view

import com.example.mashe4kinapogodaotivana.businessModel.GeoCodeModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MenuView:MvpView {
    @AddToEndSingle
    fun setLoading(flag :Boolean)

    @AddToEndSingle
    fun fillPredictionList(data: List<GeoCodeModel>)

    @AddToEndSingle
    fun fillFavoriteList(data: List<GeoCodeModel>)
}