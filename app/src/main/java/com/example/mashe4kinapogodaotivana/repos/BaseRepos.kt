package com.example.mashe4kinapogodaotivana.repos


import com.example.mashe4kinapogodaotivana.ApiProvider
import com.example.mashe4kinapogodaotivana.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepos<T>(val api:ApiProvider) {
     val dataEmitter:BehaviorSubject<T> = BehaviorSubject.create()
     protected val db by lazy { App.db}
     protected fun roomTransaction(transaction : ()-> T) = Observable.fromCallable{transaction()}.subscribeOn(
          Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{dataEmitter.onNext(it)}
}