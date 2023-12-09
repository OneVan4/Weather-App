package com.example.mashe4kinapogodaotivana.presenters

import com.example.mashe4kinapogodaotivana.view.MainView

class MainPresenter:BasePresenter<MainView>() {
    override fun enable() {

    }

    fun refresh(lat:String, long:String){
        viewState.setLoading(true)
    }
}