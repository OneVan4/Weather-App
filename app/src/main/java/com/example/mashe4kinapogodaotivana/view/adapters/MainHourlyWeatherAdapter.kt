package com.example.mashe4kinapogodaotivana.view.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mashe4kinapogodaotivana.R
import com.example.mashe4kinapogodaotivana.businessModel.HourlyWeatherModel
import com.example.mashe4kinapogodaotivana.databinding.RvitemHourlyMainBinding




class MainHourlyWeatherAdapter :BaseAdapter<HourlyWeatherModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = RvitemHourlyMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HourlyViewHolder(view)
    }

    inner class HourlyViewHolder(private val itemBinding:RvitemHourlyMainBinding) : BaseViewHolder(itemBinding.root) {

        override fun bindView(position: Int) {
            itemBinding.rvitemHourlyTimeMTV.text="21:00"
            itemBinding.rvitemHourlyProbMTV.text= "100%"
            itemBinding.rvitemHourlyWeatherIc.setImageResource(R.drawable.ic_flag_24)
            itemBinding.rvitemHourlyTempMTV.text= "22/00B0"
        }

    }

}