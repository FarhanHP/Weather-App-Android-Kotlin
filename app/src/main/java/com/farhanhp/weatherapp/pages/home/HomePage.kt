package com.farhanhp.weatherapp.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.CityDetailData
import com.farhanhp.weatherapp.databinding.PageHomeBinding

class HomePage : Fragment() {
  private lateinit var binding: PageHomeBinding
  private lateinit var viewModel: HomePageViewModel
  private lateinit var cityDetailData: CityDetailData

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.page_home, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    viewModel = HomePageViewModel()

    cityDetailData = CityDetailData(viewModel, binding.cityDetailComponent, true, viewLifecycleOwner, {
      onSearch(it)
    }, {
      moveToForecastPage(it)
    })


    // Inflate the layout for this fragment
    return binding.root
  }

  private fun onSearch(keyword: String) {
    findNavController().navigate(HomePageDirections.actionHomePageToCityListPage(keyword))
  }

  private fun moveToForecastPage(keyword: String) {
    findNavController().navigate(HomePageDirections.actionHomePageToWeatherForecastPage2(keyword))
  }
}