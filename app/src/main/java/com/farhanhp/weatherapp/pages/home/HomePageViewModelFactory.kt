package com.farhanhp.weatherapp.pages.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomePageViewModelFactory(private val context: Context): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(HomePageViewModel::class.java)) {
      return HomePageViewModel(context) as T
    }

    throw IllegalArgumentException("Unknown ViewModel class")
  }
}