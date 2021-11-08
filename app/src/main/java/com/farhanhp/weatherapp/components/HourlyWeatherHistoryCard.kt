package com.farhanhp.weatherapp.components

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.getBooleanOrThrow
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.utils.Graphic
import com.google.android.material.card.MaterialCardView
import java.lang.Exception

class HourlyWeatherHistoryCard(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs) {
  private var content: HourlyWeatherHistoryCardContent = HourlyWeatherHistoryCardContent(context, attrs)

  init {
    LayoutInflater.from(context).inflate(R.layout.component_hourly_weather_history_card, this, true)
    context.theme.obtainStyledAttributes(attrs, R.styleable.HourlyWeatherHistoryCard, 0, 0).apply {
      try{
        val isActive = getBooleanOrThrow(R.styleable.HourlyWeatherHistoryCard_isActive)
        setIsActive(isActive)
      } catch(e: Exception) {}

      val temperature = getString(R.styleable.HourlyWeatherHistoryCard_temperature)
      val time = getString(R.styleable.HourlyWeatherHistoryCard_time)

      if(temperature != null) {
        setTemperature(temperature)
      }

      if(time != null) {
        setTime(time)
      }
    }
  }

  fun setIsActive(isActive: Boolean) {
    removeAllViews()

    if(isActive) {
      content.setIsActive(true)
      val materialCardView = MaterialCardView(context, attrs)
      materialCardView.addView(content)
      addView(materialCardView)

      materialCardView.elevation = 10.0F
      materialCardView.layoutParams = (materialCardView.layoutParams as MarginLayoutParams).apply {
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.MATCH_PARENT
        setMargins(Graphic.toDp(2), Graphic.toDp(2), Graphic.toDp(2), Graphic.toDp(2))
      }
      content.setPadding(Graphic.toDp(4), Graphic.toDp(4), Graphic.toDp(4), Graphic.toDp(4))
    } else {
      addView(content)
      content.layoutParams.apply {
        height = ViewGroup.LayoutParams.MATCH_PARENT
        width = ViewGroup.LayoutParams.MATCH_PARENT
      }
      content.setPadding(Graphic.toDp(6), Graphic.toDp(6), Graphic.toDp(6), Graphic.toDp(6))
    }
  }

  fun setTemperature(temp: String) {
    content.setTemperature(temp)
  }

  fun setTime(time: String) {
    content.setTime(time)
  }

  fun setImage(img: String) {
    content.setImage(img)
  }
}