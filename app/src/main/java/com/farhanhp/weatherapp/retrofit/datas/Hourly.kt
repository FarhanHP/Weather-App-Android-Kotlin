package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class Hourly(
  val time: String,
  @Json(name = "time_epoch") val timeEpoch: Long,
  val condition: WeatherCondition,
  @Json(name ="temp_c") val tempC: Double,
  @Json(name = "wind_kph") val windKph: Double,
  @Json(name = "precip_mm") val precipMm: Double,
)
