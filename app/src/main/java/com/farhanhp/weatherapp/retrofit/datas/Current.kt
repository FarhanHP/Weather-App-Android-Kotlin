package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class Current(
  @Json(name = "last_updated_epoch") val lastUpdatedEpoch: Long,
  @Json(name = "last_updated") val lastUpdated: String,
  @Json(name = "temp_c") val tempC: Double,
  @Json(name = "is_day") val isDay: Int,
  val condition: WeatherCondition,
  @Json(name = "wind_kph") val windKph: Double,
  @Json(name = "pressure_mb") val pressureMb: Double,
  @Json(name = "precip_mm") val precipMm: Double,
  val humidity: Int,
  val cloud: Int,
  @Json(name = "gust_kph") val gustKph: Double,
  @Json(name="air_quality") val airQuality: AirQuality
)
