package com.farhanhp.weatherapp.pages.city_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityListPageViewModelFactory(
  private val keyword: String,
  private val context: Context
): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CityListPageViewModel::class.java)) {
      return CityListPageViewModel(keyword, context) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}