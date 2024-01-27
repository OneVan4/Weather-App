package com.example.mashe4kinapogodaotivana.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mashe4kinapogodaotivana.businessModel.HourlyWeatherModel


abstract class BaseAdapter<D>:RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    //Data received from server
    private val _mData by lazy { mutableListOf<D>()}
    protected val mData: List<D> =_mData

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount()= mData.size

    fun updateData(data: List<HourlyWeatherModel>){
        if(_mData.isEmpty() && data.isNotEmpty()){
            _mData.addAll(data)
        }
        else{
            _mData.clear()
            _mData.addAll(data)
        }
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view){
       abstract fun bindView(position: Int)
    }
}