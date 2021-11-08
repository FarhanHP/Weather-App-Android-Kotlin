package com.farhanhp.weatherapp.pages.city_detail

import com.farhanhp.weatherapp.classes.CityDetailViewModel

class CityDetailPageViewModel(cityName: String): CityDetailViewModel() {
  init {
    setCityName(cityName)
  }
}