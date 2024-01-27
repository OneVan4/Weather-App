package com.example.mashe4kinapogodaotivana


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mashe4kinapogodaotivana.businessModel.GeoCodeModel
import com.example.mashe4kinapogodaotivana.databinding.ActivityMenuBinding
import com.example.mashe4kinapogodaotivana.presenters.MenuPresenter
import com.example.mashe4kinapogodaotivana.view.MenuView
import com.example.mashe4kinapogodaotivana.view.adapters.CityListAdapter
import com.example.mashe4kinapogodaotivana.view.createObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.util.concurrent.TimeUnit

private lateinit var binding: ActivityMenuBinding

class MenuActivity : MvpAppCompatActivity(),MenuView {

    private val presenter by moxyPresenter {MenuPresenter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCityList(binding.predictions)
        initCityList(binding.favorites)

        presenter.enable()
        presenter.getFavoriteList()

        binding.searchField.createObservable()
            .doOnNext { setLoading(true) }
            .debounce(700,TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if(!it.isNullOrEmpty()) presenter.searchFor(it)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, androidx.constraintlayout.widget.R.anim.abc_slide_out_bottom)
    }


    private fun initCityList(recyclerView: RecyclerView){
        val cityAdapter = CityListAdapter()
        cityAdapter.clickListener = searchItemClickListener
        recyclerView.apply {
            adapter  = cityAdapter
            layoutManager = object: LinearLayoutManager(this@MenuActivity, VERTICAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            setHasFixedSize(true)

        }
    }

    override fun setLoading(flag: Boolean) {
        binding.loading.isActivated = true
        binding.loading.visibility = if(flag) View.VISIBLE else View.GONE
    }

    override fun fillPredictionList(data: List<GeoCodeModel>) {
        (binding.predictions.adapter as CityListAdapter).updateData(data)
    }

    override fun fillFavoriteList(data: List<GeoCodeModel>) {
        (binding.favorites.adapter as CityListAdapter).updateData(data)
    }


    private val searchItemClickListener= object: CityListAdapter.SearchItemClickListener{
        override fun removeFromFavorite(item: GeoCodeModel){
            presenter.removeLocation(item)
        }

        override fun showWeatherIn(item: GeoCodeModel) {
            val intent = Intent(this@MenuActivity,MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("lat",item.lat.toString())
            bundle.putString("lon",item.lon.toString())
            intent.putExtra("COORDINATES",bundle)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, androidx.constraintlayout.widget.R.anim.abc_slide_out_bottom)
        }

        override fun addToFavorite(item: GeoCodeModel){
            presenter.saveLocation(item)
        }
    }
}