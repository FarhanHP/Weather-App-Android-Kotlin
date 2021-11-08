package com.farhanhp.weatherapp.pages.city_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.CityDetailData
import com.farhanhp.weatherapp.classes.SecondaryPage
import com.farhanhp.weatherapp.databinding.PageCityDetailBinding
import com.farhanhp.weatherapp.pages.home.HomePageDirections

class CityDetailPage : SecondaryPage() {
  private lateinit var arguments: CityDetailPageArgs
  private lateinit var binding: PageCityDetailBinding
  private lateinit var viewModel: CityDetailPageViewModel
  private lateinit var viewModelFactory: CityDetailPageViewModelFactory
  private lateinit var cityDetailData: CityDetailData

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    arguments = CityDetailPageArgs.fromBundle(requireArguments())
    binding = DataBindingUtil.inflate(inflater, R.layout.page_city_detail, container, false)
    viewModelFactory = CityDetailPageViewModelFactory(arguments.keyword, context as Context)
    viewModel = ViewModelProvider(this, viewModelFactory).get(CityDetailPageViewModel::class.java)

    cityDetailData = CityDetailData(viewModel, binding.cityDetailComponent, false, viewLifecycleOwner, {
      onSearch(it)
    }) {
      moveToForecastPage(it)
    }

    cityDetailData.setOnBackHandler {
      openPriorPage()
    }

    // Inflate the layout for this fragment
    return binding.root
  }

  private fun onSearch(keyword: String) {
    findNavController().navigate(CityDetailPageDirections.actionCityDetailPageToCityListPage(keyword))
  }

  private fun moveToForecastPage(keyword: String) {
    findNavController().navigate(CityDetailPageDirections.actionCityDetailPageToWeatherForecastPage2(keyword))
  }
}