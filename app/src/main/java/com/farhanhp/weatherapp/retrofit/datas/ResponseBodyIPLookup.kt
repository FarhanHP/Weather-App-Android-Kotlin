package com.farhanhp.weatherapp.retrofit.datas

import com.squareup.moshi.Json

data class ResponseBodyIPLookup(
  val ip: String,
  val city: String,
  val region: String,
  @Json(name = "country_name") val countryName: String,
  @Json(name = "country_code") val countryCode: String,
)
