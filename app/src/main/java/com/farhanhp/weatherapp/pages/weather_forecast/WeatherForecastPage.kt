package com.farhanhp.weatherapp.pages.weather_forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.SecondaryPage
import com.farhanhp.weatherapp.components.WeatherForecastAppBar
import com.farhanhp.weatherapp.databinding.PageWeatherForecastBinding

class WeatherForecastPage: SecondaryPage() {
  private lateinit var binding: PageWeatherForecastBinding
  private lateinit var viewModel: WeatherForecastPageViewModel
  private lateinit var viewModelFactory: WeatherForecastPageViewModelFactory
  private lateinit var arguments: WeatherForecastPageArgs
  private lateinit var adapter: ForecastDayAdapter
  private lateinit var appbar: WeatherForecastAppBar
  private lateinit var weatherForecastList: RecyclerView
  private lateinit var statusTextView: TextView

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    arguments = WeatherForecastPageArgs.fromBundle(requireArguments())
    binding = DataBindingUtil.inflate(inflater, R.layout.page_weather_forecast, container, false)

    appbar = binding.appbar
    weatherForecastList = binding.weatherForecastList
    statusTextView = binding.status

    viewModelFactory = WeatherForecastPageViewModelFactory(context as Context)
    viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(WeatherForecastPageViewModel::class.java)
    viewModel.fetchWeatherForecastDays(arguments.keyword)

    adapter = ForecastDayAdapter {
      viewModel.setSelectedWeatherForecastDay(it)
      openWeatherForecastDetail()
    }
    appbar.setOnBackBtnClick {
      openPriorPage()
    }

    weatherForecastList.adapter = adapter

    viewModel.location.observe(viewLifecycleOwner) {
      if(it != null) {
        appbar.setLocation("${it.name}, ${it.country}")
      } else {
        appbar.setLocation("")
      }
    }

    viewModel.loading.observe(viewLifecycleOwner) {
      val list = viewModel.weatherForecastDays.value

      if(it) {
        weatherForecastList.visibility = View.GONE
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Loading..."
      } else if(list.isNullOrEmpty()) {
        weatherForecastList.visibility = View.GONE
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Empty Forecast Data"
      } else {
        weatherForecastList.visibility = View.VISIBLE
        statusTextView.visibility = View.GONE
        adapter.data = list
      }
    }
    return binding.root
  }

  private fun openWeatherForecastDetail() {
    findNavController().navigate(WeatherForecastPageDirections.actionWeatherForecastPageToWeatherForecastDetailPage())
  }
}