package com.farhanhp.weatherapp.pages.city_detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.farhanhp.weatherapp.classes.CityDetailViewModel
import java.lang.Exception

class CityDetailPageViewModel(cityName: String, context: Context): CityDetailViewModel() {
  init {
    try {
      setCityName(cityName)
    } catch (e: Exception) {
      Log.e("WeatherApp Error", e.toString())
      Toast.makeText(context, "Error No Internet Connection", Toast.LENGTH_LONG).show()
    }
  }
}