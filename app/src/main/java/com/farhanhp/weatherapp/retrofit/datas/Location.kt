package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class Location(
  val name: String,
  val region: String,
  val country: String,
  @Json(name="localtime") val localTime: String,
  @Json(name="localtime_epoch") val localTimeEpoch: Long
)
