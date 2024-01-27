package com.example.mashe4kinapogodaotivana

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel

class DailyInfoFragment:DailyBaseFragment<DailyWeatherModel>() {

    private lateinit var context:Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // btn_back.addOnClickListener {fm.popBackStack()}
        updateView()

       context= view.context
    }


    override fun updateView() {}

}