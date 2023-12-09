package com.example.mashe4kinapogodaotivana.repos

import com.example.mashe4kinapogodaotivana.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepos<T>(val api:ApiProvider) {
     val dataEmitter:BehaviorSubject<T> = BehaviorSubject.create()

}