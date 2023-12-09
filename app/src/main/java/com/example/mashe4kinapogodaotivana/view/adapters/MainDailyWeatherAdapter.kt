package com.example.mashe4kinapogodaotivana.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mashe4kinapogodaotivana.R
import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel
import com.example.mashe4kinapogodaotivana.databinding.RvitemDailyMainBinding


class MainDailyWeatherAdapter : BaseAdapter<DailyWeatherModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            RvitemDailyMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(view)
    }

    inner class DailyWeatherViewHolder(private val itemBinding: RvitemDailyMainBinding) :
        BaseViewHolder(itemBinding.root) {
        override fun bindView(position: Int) {
            itemBinding.rvitemDailyDateMTV.text = "21 June"
            itemBinding.rvitemDailyWeatherIcIV.setImageResource(R.drawable.ic_menu_24)
            itemBinding.rvitemDailyHumidityMTV.text= "23%"
            itemBinding.rvitemDailyMaxTemperatureMTV.text="13"
            itemBinding.rvitemDailyMinTemperatureMTV.text="5"
        }
    }
}