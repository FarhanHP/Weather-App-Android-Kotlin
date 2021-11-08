package com.farhanhp.weatherapp.pages.weather_forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanhp.weatherapp.R
import com.farhanhp.weatherapp.classes.WeatherPropertyCard2Args
import com.farhanhp.weatherapp.components.WeatherPropertyCard2Container
import com.farhanhp.weatherapp.retrofit.datas.Hourly

class ForecastHourAdapter: RecyclerView.Adapter<ForecastHourAdapter.ViewHolder>() {
  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val conditionText: TextView = view.findViewById(R.id.conditionText)
    private val conditionImage: ImageView = view.findViewById(R.id.conditionImg)
    val dateTextView: TextView = view.findViewById(R.id.date)
    val propertyContainer: WeatherPropertyCard2Container = view.findViewById(R.id.propertyContainer)

    fun setConditionImage(imgUrl: String) {
      val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
      Glide.with(conditionImage.context).load(imageUri).into(conditionImage)
    }
  }

  var data = listOf<Hourly>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.view_holder_forecast_hour, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currentData = data[position]
    holder.conditionText.text = currentData.condition.text
    holder.setConditionImage(currentData.condition.icon)
    holder.dateTextView.text = currentData.time.replace("-", "/")
    holder.propertyContainer.setChildren(listOf(
      WeatherPropertyCard2Args(
        "Temperature",
        "${currentData.tempC.toString()}℃"
      ),
      WeatherPropertyCard2Args(
        "Precipitation",
        "${currentData.precipMm} millimeters"
      ),
      WeatherPropertyCard2Args(
        "Wind Speed",
        "${currentData.windKph} KM/Hour"
      )
    ))
  }

  override fun getItemCount() = data.size
}