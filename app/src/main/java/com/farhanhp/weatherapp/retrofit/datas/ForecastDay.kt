package com.farhanhp.weatherapp.retrofit.datas

data class ForecastDay(
  val date: String,
  val day: Day,
  val hour: List<Hourly>
)
