package com.farhanhp.weatherapp.pages.weather_forecast

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherForecastPageViewModelFactory(
  private val context: Context
): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(WeatherForecastPageViewModel::class.java)) {
      return WeatherForecastPageViewModel(context) as T
    }

    throw IllegalArgumentException("Unknown ViewModel class")
  }
}