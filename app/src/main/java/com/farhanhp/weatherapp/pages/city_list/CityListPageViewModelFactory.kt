package com.farhanhp.weatherapp.pages.city_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityListPageViewModelFactory(
  private val keyword: String,
): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CityListPageViewModel::class.java)) {
      return CityListPageViewModel(keyword) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}