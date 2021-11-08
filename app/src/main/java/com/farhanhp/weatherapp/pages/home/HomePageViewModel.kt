package com.farhanhp.weatherapp.pages.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.classes.CityDetailViewModel
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import kotlinx.coroutines.launch
import java.lang.Exception

class HomePageViewModel(context: Context): CityDetailViewModel() {
  init {
    viewModelScope.launch {
      try {
        val res = WeatherAPIService.retrofitService.getIpLookup()
        val city = res.city
        setCityName(city)
      } catch (e: Exception) {
        Log.e("WeatherApp Error", e.toString())
        Toast.makeText(context, "Error No Internet Connection", Toast.LENGTH_LONG).show()
      }
    }
  }
}