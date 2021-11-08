package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.farhanhp.weatherapp.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton

class WeatherForecastAppBar(context: Context, attrs: AttributeSet): AppBarLayout(context, attrs) {
  private val backBtn: MaterialButton
  private val locationTextView: TextView

  init {
    LayoutInflater.from(context).inflate(R.layout.component_weather_forecast_appbar, this, true)

    backBtn = findViewById(R.id.backBtn)
    locationTextView = findViewById(R.id.location)
  }

  fun setLocation(location: String) {
    locationTextView.text = location
  }

  fun setOnBackBtnClick(fn: ()->Unit) {
    backBtn.setOnClickListener{
      fn()
    }
  }
}