package com.farhanhp.weatherapp.pages.city_list

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import com.farhanhp.weatherapp.retrofit.datas.SimpleLocation
import kotlinx.coroutines.launch
import java.lang.Exception

class CityListPageViewModel(private val keyword: String, context: Context): ViewModel() {
  private val _loading = MutableLiveData<Boolean>()
  var locations = listOf<SimpleLocation>()
    private set

  val loading: LiveData<Boolean> get() = _loading

  init {
    viewModelScope.launch {
      _loading.value = true
      try {
        val res = WeatherAPIService.retrofitService.search(keyword)
        locations = res.map {
          SimpleLocation(it.id, it.name, it.region, it.country, it.lat, it.lon)
        }
      } catch (e: Exception) {
        Log.e("WeatherApp Error", e.toString())
        Toast.makeText(context, "Error No Internet Connection", Toast.LENGTH_LONG).show()
      } finally {
        _loading.value = false
      }
    }
  }
}