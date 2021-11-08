package com.farhanhp.weatherapp.pages.city_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import com.farhanhp.weatherapp.retrofit.datas.SimpleLocation
import kotlinx.coroutines.launch

class CityListPageViewModel(private val keyword: String): ViewModel() {
  private val _loading = MutableLiveData<Boolean>()
  var locations = listOf<SimpleLocation>()
    private set

  val loading: LiveData<Boolean> get() = _loading

  init {
    viewModelScope.launch {
      _loading.value = true
      val res = WeatherAPIService.retrofitService.search(keyword)
      locations = res.map {
        SimpleLocation(it.id, it.name, it.region, it.country, it.lat, it.lon)
      }
      _loading.value = false
    }
  }
}