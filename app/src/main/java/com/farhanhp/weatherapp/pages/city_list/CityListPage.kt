package com.farhanhp.weatherapp.pages.city_list

import android.os.Bundle
import android.util.Log
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
import com.farhanhp.weatherapp.components.CityListAppBar
import com.farhanhp.weatherapp.databinding.PageCityListBinding

class CityListPage : SecondaryPage() {
  private lateinit var binding: PageCityListBinding
  private lateinit var appbar: CityListAppBar
  private lateinit var arguments: CityListPageArgs
  private lateinit var viewModel: CityListPageViewModel
  private lateinit var viewModelFactory: CityListPageViewModelFactory
  private lateinit var adapter: CityAdapter
  private lateinit var statusTextView: TextView
  private lateinit var cityList: RecyclerView

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    arguments = CityListPageArgs.fromBundle(requireArguments())
    binding = DataBindingUtil.inflate(inflater, R.layout.page_city_list, container,  false)
    viewModelFactory = CityListPageViewModelFactory(arguments.keyword)
    viewModel = ViewModelProvider(this, viewModelFactory).get(CityListPageViewModel::class.java)
    statusTextView = binding.status
    appbar = binding.appbar
    appbar.setTitle(arguments.keyword)
    appbar.setBackBtnListener {
      openPriorPage()
    }
    cityList = binding.cityList
    adapter = CityAdapter{
      handleSimpleLocationCardClick(it)
    }
    viewModel.loading.observe(viewLifecycleOwner) {
      val locations = viewModel.locations

      if(it) {
        cityList.visibility = View.GONE
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Loading..."
      } else if(locations.isEmpty()) {
        cityList.visibility = View.GONE
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Empty Result"
      } else {
        cityList.visibility = View.VISIBLE
        statusTextView.visibility = View.GONE
        adapter.data = locations
      }
    }

    cityList.adapter = adapter

    // Inflate the layout for this fragment
    return binding.root
  }

  private fun handleSimpleLocationCardClick(name: String) {
    findNavController().navigate(CityListPageDirections.actionCityListPageToCityDetailPage(name))
  }
}