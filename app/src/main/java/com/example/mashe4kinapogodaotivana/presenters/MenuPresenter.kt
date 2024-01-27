package com.example.mashe4kinapogodaotivana.presenters

import androidx.constraintlayout.motion.utils.ViewState
import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.businessModel.GeoCodeModel
import com.example.mashe4kinapogodaotivana.repos.SAVED
import com.example.mashe4kinapogodaotivana.repos.menuRepository
import com.example.mashe4kinapogodaotivana.view.MenuView

class MenuPresenter:BasePresenter<MenuView>() {
    private val repo = menuRepository(ApiProvider())

    override fun enable() {
        repo.dataEmitter.subscribe{
            viewState.setLoading(false)
            if(it.purpose == SAVED){
                viewState.fillFavoriteList(it.Data)
            }
            else{
                viewState.fillPredictionList(it.Data)
            }
        }

    }

    fun searchFor(s:String){
        repo.GetCities(s)
    }

    fun removeLocation(data:GeoCodeModel){
        repo.remove(data)
    }

    fun saveLocation (data: GeoCodeModel){
        repo.add(data)
    }

    fun getFavoriteList (){
        repo.update()
    }
}