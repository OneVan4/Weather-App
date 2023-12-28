package com.example.mashe4kinapogodaotivana.view.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mashe4kinapogodaotivana.R
import com.example.mashe4kinapogodaotivana.businessModel.HourlyWeatherModel
import com.example.mashe4kinapogodaotivana.databinding.RvitemHourlyMainBinding
import com.example.mashe4kinapogodaotivana.view.DAY_WEEK_NAME_LONG
import com.example.mashe4kinapogodaotivana.view.toDateFormatOF
import com.example.mashe4kinapogodaotivana.view.toDegree


class MainHourlyWeatherAdapter :BaseAdapter<HourlyWeatherModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = RvitemHourlyMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HourlyViewHolder(view)
    }

    inner class HourlyViewHolder(private val itemBinding:RvitemHourlyMainBinding) : BaseViewHolder(itemBinding.root) {

        override fun bindView(position: Int) {
            mData[position].apply {
                itemBinding.rvitemHourlyTimeMTV.text=dt.toDateFormatOF(DAY_WEEK_NAME_LONG)
                itemBinding.rvitemHourlyProbMTV.text= pop.toString()
                itemBinding.rvitemHourlyWeatherIc.setImageResource(R.drawable.ic_flag_24)
                itemBinding.rvitemHourlyTempMTV.text= java.lang.StringBuilder().append(temp.toDegree()).append("/00B0").toString()
            }
        }

    }

}