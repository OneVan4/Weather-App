package com.example.mashe4kinapogodaotivana

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel

abstract class DailyBaseFragment<T>: Fragment(){
    protected var mData:T? = null
    protected lateinit var fm:FragmentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fm= (context as FragmentActivity).supportFragmentManager
    }

    fun setData(data: List<DailyWeatherModel>){
        mData=data
        if(isVisible)
        {
            updateView()
        }
    }

    abstract fun updateView()
}