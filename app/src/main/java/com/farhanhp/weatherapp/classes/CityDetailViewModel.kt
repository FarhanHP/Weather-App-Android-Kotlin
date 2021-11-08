package com.farhanhp.weatherapp.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhanhp.weatherapp.retrofit.WeatherAPIService
import com.farhanhp.weatherapp.retrofit.datas.AirQuality
import com.farhanhp.weatherapp.retrofit.datas.Current
import com.farhanhp.weatherapp.retrofit.datas.Hourly
import com.farhanhp.weatherapp.retrofit.datas.Location
import com.farhanhp.weatherapp.utils.CustomDate
import kotlinx.coroutines.launch

abstract class CityDetailViewModel: ViewModel() {
  private val _currentData = MutableLiveData<Current>()
  private val _locationData = MutableLiveData<Location>()
  private val _airQualityData = MutableLiveData<AirQuality>()
  private val _hourlyHistoryData = MutableLiveData<List<Hourly>>()
  private val _cityName = MutableLiveData<String>()

  val currentData: LiveData<Current> get() = _currentData
  val locationData: LiveData<Location> get() = _locationData
  val airQualityData: LiveData<AirQuality> get() = _airQualityData
  val hourlyHistoryData: LiveData<List<Hourly>> get() = _hourlyHistoryData
  val cityName: LiveData<String> get() = _cityName

  private fun getPastHourly(n: Int, rawList: List<Hourly>, localTime: String): List<Hourly> {
    val outputLength = if (n>24) 24 else n
    val nowHour = CustomDate(localTime.replace("-", "/")).hours
    if(nowHour >= outputLength) {
      val startIndex = nowHour - outputLength + 1
      return rawList.subList(startIndex, outputLength+startIndex)
    }

    return rawList.subList(0, outputLength)
  }

  fun setCityName(cityName: String) {
    _cityName.value = cityName

    viewModelScope.launch {
      val resCurrent = WeatherAPIService.retrofitService.getCurrentByCityName(cityName)
      _currentData.value = resCurrent.current
      _locationData.value = resCurrent.location
      _airQualityData.value = resCurrent.current.airQuality

      val resHistory = WeatherAPIService.retrofitService.getHistoryByCityName(cityName, CustomDate().toYYYYMMdd())
      _hourlyHistoryData.value = getPastHourly(6, resHistory.forecast.forecastday[0].hour, resCurrent.location.localTime)
    }
  }
}