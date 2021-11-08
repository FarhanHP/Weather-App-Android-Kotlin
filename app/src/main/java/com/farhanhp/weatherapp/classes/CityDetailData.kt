package com.farhanhp.weatherapp.classes

import androidx.lifecycle.LifecycleOwner
import com.farhanhp.weatherapp.components.CityDetail
import com.farhanhp.weatherapp.utils.CustomDate

class CityDetailData(
  private val viewModel: CityDetailViewModel,
  private val cityDetailComponent: CityDetail,
  private val isHome: Boolean,
  private val viewLifecycleOwner: LifecycleOwner,
  private val onSearchHandler: (keyword: String)->Unit,
  private val onWeatherForecastBtnClick: (keyword: String)->Unit,
) {

  init {
    // I don't understand why I receiving error when passing livedata to CityDetail component in xml, so i passed it programmatically
    viewModel.currentData.observe(viewLifecycleOwner) {
      cityDetailComponent.setTemperature(it.tempC)
      cityDetailComponent.setWindKph(it.windKph)
      cityDetailComponent.setPressureMb(it.pressureMb)
      cityDetailComponent.setPrecipMm(it.precipMm)
      cityDetailComponent.setHumidity(it.humidity)
      cityDetailComponent.setCloud(it.cloud)
      cityDetailComponent.setGustKph(it.gustKph)
      cityDetailComponent.setWeatherConditionText(it.condition.text)
      cityDetailComponent.setWeatherConditionImage(it.condition.icon)
      cityDetailComponent.setLastUpdated(
        CustomDate(
          it.lastUpdated.replace("-", "/")
        ).toLocaleString()
      )
    }

    viewModel.locationData.observe(viewLifecycleOwner) {
      cityDetailComponent.setLocalDate(CustomDate(it.localTime.replace("-", "/")))
      cityDetailComponent.setLocation("${it.name}, ${it.country}")
      cityDetailComponent.setIsHome(isHome)
    }

    viewModel.airQualityData.observe(viewLifecycleOwner) {
      cityDetailComponent.setDefraIndex(it.defraIndex)
      cityDetailComponent.setEpaIndex(it.epaIndex)
      cityDetailComponent.setCo(it.co)
      cityDetailComponent.setO3(it.o3)
      cityDetailComponent.setNo2(it.no2)
      cityDetailComponent.setSo2(it.so2)
      cityDetailComponent.setPm10(it.pm10)
      cityDetailComponent.setPm25(it.pm25)
    }

    viewModel.hourlyHistoryData.observe(viewLifecycleOwner) {
      cityDetailComponent.setWeatherHourlyHistory(it)
    }

    viewModel.cityName.observe(viewLifecycleOwner) {
      cityDetailComponent.setOnWeatherForecastBtnClick {
        onWeatherForecastBtnClick(it)
      }
    }

    cityDetailComponent.setSearchHandler {
      onSearchHandler(it)
    }
  }

  fun setOnBackHandler(fn: ()->Unit) {
    cityDetailComponent.setBackBtnListener(fn)
  }
}