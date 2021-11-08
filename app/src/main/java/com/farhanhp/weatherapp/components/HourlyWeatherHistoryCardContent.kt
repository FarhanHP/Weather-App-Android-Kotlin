package com.farhanhp.weatherapp.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.farhanhp.weatherapp.R

class HourlyWeatherHistoryCardContent(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
  private val temperatureTextView: TextView
  private val timeTextView: TextView
  private val imageView: ImageView

  private val defaultTextViewColor: ColorStateList

  init {
    LayoutInflater.from(context).inflate(R.layout.component_hourly_weather_history_card_content, this, true)

    temperatureTextView = findViewById(R.id.hourlyWeatherHistoryCard_temperature)
    timeTextView = findViewById(R.id.hourlyWeatherHistoryCard_time)
    imageView = findViewById(R.id.hourlyWeatherHistoryCard_image)

    defaultTextViewColor = timeTextView.textColors

    context.theme.obtainStyledAttributes(attrs, R.styleable.HourlyWeatherHistoryCard, 0, 0).apply {
      val isActive = getBoolean(R.styleable.HourlyWeatherHistoryCard_isActive, false)
      val temperature = getString(R.styleable.HourlyWeatherHistoryCard_temperature)
      val time = getString(R.styleable.HourlyWeatherHistoryCard_time)

      setIsActive(isActive)

      if(temperature != null) {
        setTemperature(temperature)
      }

      if(time != null) {
        setTime(time)
      }
    }
  }

  fun setIsActive(isActive: Boolean) {
    if(isActive) {
      timeTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
      temperatureTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
    } else {
      timeTextView.setTextColor(defaultTextViewColor)
      temperatureTextView.setTextColor(defaultTextViewColor)
    }
  }

  fun setTemperature(temp: String) {
    temperatureTextView.text = temp
  }

  fun setTime(time: String) {
    timeTextView.text = time
  }

  fun setImage(imageUrl: String) {
    val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(imageView.context).load(imageUri).into(imageView)
  }
}