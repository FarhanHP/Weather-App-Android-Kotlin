package com.farhanhp.weatherapp.pages.weather_forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import com.farhanhp.weatherapp.retrofit.datas.Day
import com.farhanhp.weatherapp.retrofit.datas.ForecastDay
import com.farhanhp.weatherapp.retrofit.datas.Location
import kotlinx.coroutines.launch

class WeatherForecastPageViewModel(): ViewModel() {
  private val _loading = MutableLiveData<Boolean>()
  private val _selectedWeatherForecastDay = MutableLiveData<ForecastDay>()
  private val _weatherForecastDays = MutableLiveData<List<ForecastDay>>()
  private val _location = MutableLiveData<Location?>()

  val selectedWeatherForecastDay: LiveData<ForecastDay> get() = _selectedWeatherForecastDay
  val weatherForecastDays: LiveData<List<ForecastDay>> get() = _weatherForecastDays
  val loading: LiveData<Boolean> get() = _loading
  val location: LiveData<Location?> get() = _location

  fun setSelectedWeatherForecastDay(forecastDay: ForecastDay) {
    _selectedWeatherForecastDay.value = forecastDay
  }

  fun fetchweatherForecastDays(keyword: String) {
    _location.value = null
    _weatherForecastDays.value = null

    viewModelScope.launch {
      _loading.value = true
      val res = WeatherAPIService.retrofitService.getForecastListByLocation(keyword)
      _location.value = res.location
      _weatherForecastDays.value = res.forecast.forecastday
      _loading.value = false
    }
  }
}