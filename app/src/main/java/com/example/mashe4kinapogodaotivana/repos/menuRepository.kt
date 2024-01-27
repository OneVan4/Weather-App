package com.example.mashe4kinapogodaotivana.repos

import android.app.DownloadManager.Query
import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.businessModel.GeoCodeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

const val  SAVED = 1
const val CURRENT = 0

class menuRepository(api:ApiProvider):BaseRepos<menuRepository.Content>(api) {

    private val dbAccess = db.getGeoCodeDao()

    fun GetCities(name:String){
        api.provideGeoCodeAPI().getCityByCoordinates(name,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{
                Content(it, CURRENT)
            }
            .subscribe{
                dataEmitter.onNext(it)
            }
    }

    fun add(data : GeoCodeModel){
        getFavoriteListWith {(dbAccess.add(data.mapToEntity()))}

    }

    fun remove(data : GeoCodeModel){
        getFavoriteListWith {(dbAccess.remove(data.mapToEntity()))}
    }

    fun update(){
        getFavoriteListWith()
    }

    private fun getFavoriteListWith(daoQuery:(()->Unit)?=null){
        roomTransaction {
            daoQuery?.let { it() }
            Content(dbAccess.getAll().map { it.mapToModel() }, SAVED)
        }
    }

    data class Content(val Data:List<GeoCodeModel>,val purpose:Int)
}
