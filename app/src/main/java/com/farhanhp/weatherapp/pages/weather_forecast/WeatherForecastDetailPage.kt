package com.farhanhp.weatherapp.pages.weather_forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.SecondaryPage
import com.farhanhp.weatherapp.classes.WeatherPropertyCard2Args
import com.farhanhp.weatherapp.components.WeatherForecastAppBar
import com.farhanhp.weatherapp.components.WeatherPropertyCard2Container
import com.farhanhp.weatherapp.databinding.PageWeatherForecastDetailBinding
import com.farhanhp.weatherapp.utils.CustomDate

class WeatherForecastDetailPage : SecondaryPage() {
  private lateinit var binding: PageWeatherForecastDetailBinding
  private lateinit var viewModelFactory: WeatherForecastPageViewModelFactory
  private lateinit var viewModel: WeatherForecastPageViewModel
  private lateinit var appbar: WeatherForecastAppBar
  private lateinit var weatherConditionImage: ImageView
  private lateinit var weatherConditionText: TextView
  private lateinit var weatherForecastDayInfo: WeatherPropertyCard2Container
  private lateinit var weatherForecastHourlyList: RecyclerView
  private lateinit var weatherForecastHourAdapter: ForecastHourAdapter

  companion object {
    val WEATHER_ATTRIBUTES = arrayOf("Max Temperature", "Min Temperature", "Avg Temperature", "Max Wind Speed", "Total Precipitation")
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.page_weather_forecast_detail, container, false)

    appbar = binding.appbar
    appbar.setOnBackBtnClick {
      openPriorPage()
    }
    weatherConditionText = binding.weatherConditionText
    weatherConditionImage = binding.weatherConditionImage
    weatherForecastDayInfo = binding.weatherForecastDayInfo
    weatherForecastHourlyList = binding.weatherForecastHourlyList
    weatherForecastHourAdapter = ForecastHourAdapter()
    weatherForecastHourlyList.adapter = weatherForecastHourAdapter

    viewModelFactory = WeatherForecastPageViewModelFactory(context as Context)
    viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(WeatherForecastPageViewModel::class.java)
    viewModel.selectedWeatherForecastDay.observe(viewLifecycleOwner) {
      val location = viewModel.location.value
      val weatherForecastDay = it.day
      val weatherForecastDayCondition = weatherForecastDay.condition

      if (location != null) {
        appbar.setLocation(
          "${location.name}, ${location.country} (${
            CustomDate(
              it.date.replace(
                "-",
                "/"
              )
            ).toYYYYMMdd2()
          })"
        )
      }

      setWeatherInfoImage(weatherForecastDayCondition.icon)
      weatherConditionText.text = weatherForecastDayCondition.text
      val weatherPropertyCard2ArgsList = mutableListOf<WeatherPropertyCard2Args>()

      for (i in WEATHER_ATTRIBUTES) {
        weatherPropertyCard2ArgsList.add(
          WeatherPropertyCard2Args(
            i,
            when (i) {
              "Max Temperature" -> "${weatherForecastDay.maxtempC}℃"
              "Min Temperature" -> "${weatherForecastDay.mintempC}℃"
              "Avg Temperature" -> "${weatherForecastDay.avgtempC}℃"
              "Max Wind Speed" -> "${weatherForecastDay.maxwindKph} KM/Hour"
              else -> "${weatherForecastDay.totalprecipMm} millimeters"
            }
          )
        )
      }

      weatherForecastDayInfo.setChildren(weatherPropertyCard2ArgsList)
      weatherForecastHourAdapter.data = it.hour
    }

    // Inflate the layout for this fragment
    return binding.root
  }

  private fun setWeatherInfoImage(imageUrl: String) {
    val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(weatherConditionImage.context).load(imageUri).into(weatherConditionImage)
  }
}