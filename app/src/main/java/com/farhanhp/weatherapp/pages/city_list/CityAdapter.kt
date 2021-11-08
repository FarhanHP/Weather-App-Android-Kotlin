package com.farhanhp.weatherapp.pages.city_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.retrofit.datas.SimpleLocation
import com.google.android.material.card.MaterialCardView

class CityAdapter(private val cardOnClickHandler: (name: String) -> Unit): RecyclerView.Adapter<CityAdapter.ViewHolder>() {
  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val root: MaterialCardView = view.findViewById(R.id.root)
    val cityName: TextView = view.findViewById(R.id.cityName)
    val region: TextView = view.findViewById(R.id.region)
    val country: TextView = view.findViewById(R.id.country)
    val longitude: TextView = view.findViewById(R.id.longitude)
    val latitude: TextView = view.findViewById(R.id.latitude)
  }

  var data = listOf<SimpleLocation>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.view_holder_city, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currentData = data[position]
    holder.root.setOnClickListener{
      cardOnClickHandler(currentData.name)
    }
    holder.cityName.text = currentData.name.split(", ")[0]
    holder.region.text = currentData.region
    holder.country.text = currentData.country
    holder.longitude.text = currentData.lon.toString()
    holder.latitude.text = currentData.lat.toString()
  }

  override fun getItemCount() = data.size
}