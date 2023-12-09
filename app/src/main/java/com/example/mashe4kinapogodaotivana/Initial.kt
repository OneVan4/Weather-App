package com.example.mashe4kinapogodaotivana

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val GEO_LOCATION_REQ_CODE_SUCCESS = 1

class Initial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
    }


    //Initial activity code
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == GEO_LOCATION_REQ_CODE_SUCCESS && permissions.isNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
    }

    private fun checkPermission (){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
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
}