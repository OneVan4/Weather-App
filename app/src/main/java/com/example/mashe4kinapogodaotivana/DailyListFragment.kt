package com.example.mashe4kinapogodaotivana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel
import com.example.mashe4kinapogodaotivana.view.adapters.MainDailyWeatherAdapter

class DailyListFragment:DailyBaseFragment<DailyWeatherModel>() {

    private val dailyAdapter=MainDailyWeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dailyList:RecyclerView = view.findViewById(R.id.fragment_container)
        dailyList.apply{
            adapter = dailyAdapter
            layoutManager=LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            dailyAdapter.clickListener = clickListener
        }
        mData?.let { updateView() }
    }

    override fun updateView() {
        dailyAdapter.updateData(mData!!)
    }

    private val clickListener = object: MainDailyWeatherAdapter.DayItemClickListener{
        override fun showDetail(data: DailyWeatherModel) {
            val fragmentToGo =DailyInfoFragment()
            fragmentToGo.setData(data)
            fm.beginTransaction().replace(R.id.fragment_container,fragmentToGo).addToBackStack(null).commit()
        }

    }

}