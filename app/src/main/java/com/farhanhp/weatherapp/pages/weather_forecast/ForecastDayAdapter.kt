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
import com.farhanhp.weatherapp.retrofit.datas.ForecastDay
import com.farhanhp.weatherapp.utils.CustomDate
import com.google.android.material.card.MaterialCardView

class ForecastDayAdapter(private val cardOnClickHandler: (forecastDay: ForecastDay) -> Unit): RecyclerView.Adapter<ForecastDayAdapter.ViewHolder>() {
  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val rootView: MaterialCardView = view.findViewById(R.id.root)
    private val dateTextView: TextView = view.findViewById(R.id.date)
    private val conditionTextView: TextView = view.findViewById(R.id.conditionText)
    private val temperatureTextView: TextView = view.findViewById(R.id.temperature)
    private val conditionImageView: ImageView = view.findViewById(R.id.conditionImg)

    fun setOnRootViewClick(fn: ()->Unit) {
      rootView.setOnClickListener{
        fn()
      }
    }

    fun setDate(date: String) {
      dateTextView.text = date
    }

    fun setConditionText(text: String) {
      conditionTextView.text = text
    }

    fun setTemperature(temp: String) {
      temperatureTextView.text = temp
    }

    fun setConditionImage(imgUrl: String) {
      val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
      Glide.with(conditionImageView.context).load(imageUri).into(conditionImageView)
    }
  }

  var data = listOf<ForecastDay>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.view_holder_forecast_day, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currentData = data[position]
    holder.setConditionImage(currentData.day.condition.icon)
    holder.setConditionText(currentData.day.condition.text)
    holder.setTemperature("${currentData.day.avgtempC.toString()}℃")
    holder.setDate(CustomDate(currentData.date.replace("-", "/")).toLocaleStringWithoutHourMinute())
    holder.setOnRootViewClick {
      cardOnClickHandler(currentData)
    }
  }

  override fun getItemCount() = data.size
}