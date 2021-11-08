package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.WeatherPropertyCard2Args

class WeatherPropertyCard2Container(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs) {
  init {
    LayoutInflater.from(context).inflate(R.layout.component_weather_property_card2_container, this, true)
  }

  fun setChildren(argsList: List<WeatherPropertyCard2Args>) {
    removeAllViews()
    for(i in argsList) {
      val newView = WeatherPropertyCard2(context, attrs)
      addView(newView)
      newView.setValue(i.value)
      newView.setTitle(i.title)
    }
  }
}