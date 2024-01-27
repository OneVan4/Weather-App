package com.example.mashe4kinapogodaotivana


import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mashe4kinapogodaotivana.businessModel.DailyWeatherModel
import com.example.mashe4kinapogodaotivana.businessModel.HourlyWeatherModel
import com.example.mashe4kinapogodaotivana.businessModel.WeatherDataModel
import com.example.mashe4kinapogodaotivana.databinding.ActivityMainBinding
import com.example.mashe4kinapogodaotivana.presenters.MainPresenter
import com.example.mashe4kinapogodaotivana.view.*
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

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,DailyListFragment(),DailyListFragment::class.simpleName).commit()

        checkPermission()
        binding.mainHourlyWeatherRV.apply {
            adapter = MainHourlyWeatherAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    override fun displayCurrentDate(data: WeatherDataModel) {
        data.apply {
            binding.mainDateTV.text = current.dt.toDateFormatOF(DAY_FULL_MONTH_NAME)
            binding.mainTemperatureTV.text=current.temp.toDegree()
            daily[0].temp.apply {
                binding.mainMaxWeatherTemperature.text =max.toDegree()
                binding.mainMinWeatherTemperature.text=min.toDegree()
            }
            binding.mainPressureMTV.text= java.lang.StringBuilder().append(current.pressure.toString()).append("hPA").toString()
            binding.mainHumidityMTV.text= java.lang.StringBuilder().append(current.humidity.toString()).append("%").toString()
            binding.mainWindSpeedMTV.text=java.lang.StringBuilder().append(current.wind_speed.toString()).append("m/s").toString()
            binding.mainSunsetMTV.text = current.sunset.toDateFormatOF(HOUR_DOUBLE_DOT_MINUTE)
            binding.mainSunriseMTV.text = current.sunrise.toDateFormatOF(HOUR_DOUBLE_DOT_MINUTE)
        }
    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyWeatherRV.adapter as MainHourlyWeatherAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (supportFragmentManager.findFragmentByTag( DailyListFragment::class.simpleName)as DailyListFragment).setData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }

    //end of the moxy Code
}