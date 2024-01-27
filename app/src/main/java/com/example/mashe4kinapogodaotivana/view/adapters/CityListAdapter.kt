package com.example.mashe4kinapogodaotivana.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mashe4kinapogodaotivana.R
import com.example.mashe4kinapogodaotivana.businessModel.GeoCodeModel
import com.example.mashe4kinapogodaotivana.databinding.RvItemCityListBinding
import com.example.mashe4kinapogodaotivana.databinding.RvitemDailyMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class CityListAdapter:BaseAdapter<GeoCodeModel>() {

    lateinit var clickListener : SearchItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder {
       val view =  RvItemCityListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitySearchViewHolder(view)
    }

    interface SearchItemClickListener {
        fun addToFavorite(item:GeoCodeModel)

        fun removeFromFavorite(item: GeoCodeModel)

        fun showWeatherIn(item: GeoCodeModel)
    }

    @SuppressLint("NonConstantResourceId")
    inner class CitySearchViewHolder(private val itemBinding: RvItemCityListBinding):BaseViewHolder(itemBinding.root){

        var mCity = itemBinding.searchCity

        var mCountry = itemBinding.searchCountry

        var mFavorite = itemBinding.favorite

        var mLocation = itemBinding.location

        var mState = itemBinding.state

        override fun bindView(position: Int) {
            mLocation.setOnClickListener {
                clickListener.showWeatherIn(mData[position])
            }

            mFavorite.setOnClickListener {
                val item = mData[position]
                when ((it as MaterialButton).isChecked){
                    true -> {
                        item.isFavorite = true
                        clickListener.addToFavorite(item)
                    }
                    false ->{
                        item.isFavorite = false
                        clickListener.removeFromFavorite(item)
                    }
                }
                mData[position].apply {
                    mState.text = if(!state.isNullOrEmpty()) ", {state}" else ""
                    mCity.text = local_names.ru
                    mCountry.text= country
                    mFavorite.isChecked = isFavorite
                }
            }


        }
    }
}