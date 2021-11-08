package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class Day(
  @Json(name="maxtemp_c") val maxtempC: Double,
  @Json(name="mintemp_c") val mintempC: Double,
  @Json(name="avgtemp_c") val avgtempC: Double,
  @Json(name="maxwind_kph") val maxwindKph: Double,
  @Json(name="totalprecip_mm") val totalprecipMm: Double,
  val condition: WeatherCondition,
)
