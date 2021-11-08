package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.farhanhp.weatherapp.R

class WeatherPropertyCard(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
  private val titleTextView: TextView
  private val valueTextView: TextView

  init {
    LayoutInflater.from(context).inflate(R.layout.component_weather_property_card, this, true)

    titleTextView = findViewById(R.id.weatherPropertyCardTitle)
    valueTextView = findViewById(R.id.weatherPropertyCardValue)

    context.theme.obtainStyledAttributes(attrs, R.styleable.WeatherPropertyCard, 0, 0).apply {
      try {
        val title = getString(R.styleable.WeatherPropertyCard_title)
        val value = getString(R.styleable.WeatherPropertyCard_value)

        if(title != null) {
          setTitle(title)
        }

        if(value != null) {
          setValue(value)
        }
      } finally {
        recycle()
      }
    }
  }

  fun setTitle(title: String) {
    titleTextView.text = title
  }

  fun setValue(value: String) {
    valueTextView.text = value
  }
}