package com.farhanhp.weatherapp.pages.home

import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.classes.CityDetailViewModel
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import kotlinx.coroutines.launch

class HomePageViewModel: CityDetailViewModel() {
  init {
    viewModelScope.launch {
      val res = WeatherAPIService.retrofitService.getIpLookup()
      val city = res.city
      setCityName(city)
    }
  }
}