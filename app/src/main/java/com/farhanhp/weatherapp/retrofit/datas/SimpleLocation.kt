package com.farhanhp.weatherapp.retrofit.datas

data class SimpleLocation(
  val id: Long,
  val name: String,
  val region: String,
  val country: String,
  val lat: Double,
  val lon: Double,
)
