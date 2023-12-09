package com.example.mashe4kinapogodaotivana


import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mashe4kinapogodaotivana.databinding.ActivityMainBinding
import com.example.mashe4kinapogodaotivana.presenters.MainPresenter
import com.example.mashe4kinapogodaotivana.view.MainView
import com.example.mashe4kinapogodaotivana.view.adapters.MainDailyWeatherAdapter
import com.example.mashe4kinapogodaotivana.view.adapters.MainHourlyWeatherAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter



class MainActivity :MvpAppCompatActivity(), MainView {


    private val mainPresenter by moxyPresenter { MainPresenter() }

    private lateinit var binding: ActivityMainBinding
    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
        binding.mainHourlyWeatherRV.apply {
            adapter = MainHourlyWeatherAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        binding.mainDailyWeatherRV.apply {
            adapter = MainDailyWeatherAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        mainPresenter.enable()
        geoService.requestLocationUpdates(locationRequest, geoCallBack, null)
    }

    //all the Location Code here!:
    private fun initLocationRequest(): LocationRequest {
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 10000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }


    private val geoCallBack = object : LocationCallback() {
        override fun onLocationResult(geoLocation: LocationResult) {
            for (geo in geoLocation.locations) {
                mLocation = geo
                mainPresenter.refresh(mLocation.latitude.toString(),mLocation.longitude.toString())
            }
        }
    }

    //end of the Location Code

    //Initial activity code
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkPermission (){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ){
            MaterialAlertDialogBuilder(this)
                .setTitle("Разрешение")
                .setMessage("Для работы приложения вам необходимо предоставить доступ к данным вашей локации")
                .setPositiveButton("Ok"){_,_->
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQ_CODE_SUCCESS)
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQ_CODE_SUCCESS)
                }
                .setNegativeButton("Cancel"){ dialog,_->dialog.dismiss() }
                .create()
                .show()
        }
    }

    //moxy Code
    override fun displayLocation(data: String) {
      binding.mainMyCityTV.text=data
    }

    override fun displayCurrentDate(data: WeatherData) {

    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyWeatherRV.adapter as MainHourlyWeatherAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyWeatherRV.adapter as MainDailyWeatherAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }

    //end of the moxy Code
}