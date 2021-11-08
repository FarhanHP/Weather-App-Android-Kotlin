package com.farhanhp.weatherapp.pages.weather_forecast

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import com.farhanhp.weatherapp.retrofit.datas.Day
import com.farhanhp.weatherapp.retrofit.datas.ForecastDay
import com.farhanhp.weatherapp.retrofit.datas.Location
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherForecastPageViewModel(
  private val context: Context
): ViewModel() {
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

  fun fetchWeatherForecastDays(keyword: String) {
    _location.value = null
    _weatherForecastDays.value = null

    viewModelScope.launch {
      _loading.value = true
      try {
        val res = WeatherAPIService.retrofitService.getForecastListByLocation(keyword)
        _location.value = res.location
        _weatherForecastDays.value = res.forecast.forecastday
      } catch (e: Exception) {
        Log.e("WeatherApp Error", e.toString())
        Toast.makeText(context, "Error No Internet Connection", Toast.LENGTH_LONG).show()
      } finally {
        _loading.value = false
      }
    }
  }
}