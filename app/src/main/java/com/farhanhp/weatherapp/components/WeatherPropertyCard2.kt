package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.farhanhp.weatherapp.R

class WeatherPropertyCard2(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
  val titleTextView: TextView
  val valueTextView: TextView

  init {
    LayoutInflater.from(context).inflate(R.layout.component_weather_property_card2, this, true)
    titleTextView = findViewById(R.id.title)
    valueTextView = findViewById(R.id.value)
  }

  fun setTitle(title: String) {
    titleTextView.text = title
  }

  fun setValue(value: String) {
    valueTextView.text = value
  }
}