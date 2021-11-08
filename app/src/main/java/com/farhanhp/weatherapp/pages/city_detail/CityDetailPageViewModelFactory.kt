package com.farhanhp.weatherapp.pages.city_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityDetailPageViewModelFactory(
  private val keyword: String
): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CityDetailPageViewModel::class.java)) {
      return CityDetailPageViewModel(keyword) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}