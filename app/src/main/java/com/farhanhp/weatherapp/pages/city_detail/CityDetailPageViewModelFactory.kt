package com.farhanhp.weatherapp.pages.city_detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CityDetailPageViewModelFactory(
  private val keyword: String,
  private val context: Context
): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CityDetailPageViewModel::class.java)) {
      return CityDetailPageViewModel(keyword, context) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}