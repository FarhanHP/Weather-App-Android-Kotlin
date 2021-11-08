package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class AirQuality(
  val co: Double,
  val no2: Double,
  val o3: Double,
  val so2: Double,
  @Json(name="pm2_5") val pm25: Double,
  val pm10: Double,
  @Json(name="us-epa-index") val epaIndex: Int,
  @Json(name="gb-defra-index") val defraIndex: Int,
)
