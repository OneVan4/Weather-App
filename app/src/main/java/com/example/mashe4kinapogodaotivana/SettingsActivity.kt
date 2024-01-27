package com.example.mashe4kinapogodaotivana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import com.example.mashe4kinapogodaotivana.databinding.ActivityMainBinding
import com.example.mashe4kinapogodaotivana.databinding.ActivitySettingsBinding
import com.example.mashe4kinapogodaotivana.view.adapters.SettingsHolder
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding.innerToolBar.setNavigationOnClickListener { onBackPressed() }

        setSavedSettings()

        listOf(binding.GroupTemp,binding.windSpeed,binding.pressure).forEach{
            it.addOnButtonCheckedListener(ToggleButtonClickListener)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        SettingsHolder.onDestroy()
    }

    private fun setSavedSettings(){
        binding.GroupTemp.check(SettingsHolder.windSpeed.checkedViewId)
        binding.windSpeed.check(SettingsHolder.windSpeed.checkedViewId)
        binding.pressure.check(SettingsHolder.windSpeed.checkedViewId)
    }

    private object ToggleButtonClickListener:MaterialButtonToggleGroup.OnButtonCheckedListener{
        override fun onButtonChecked(
            group: MaterialButtonToggleGroup?,
            checkedId: Int,
            isChecked: Boolean
        ) {
           when(checkedId to isChecked) {
               R.id.degreeC to true -> SettingsHolder.temp = SettingsHolder.Settings.TEMP_CELSIUS
               //И т.д.
               else -> {}
           }
        }

    }
}