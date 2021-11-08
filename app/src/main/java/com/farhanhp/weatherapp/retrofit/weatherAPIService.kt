package com.farhanhp.weatherapp.retrofit

import com.farhanhp.weatherapp.retrofit.datas.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com/v1/"
private const val API_KEY = "151c1b40a21644debba31126212610"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface WeatherAPI {
  @GET("ip.json?key=$API_KEY&q=auto:ip")
  suspend fun getIpLookup(): ResponseBodyIPLookup

  @GET("current.json?key=$API_KEY&aqi=yes")
  suspend fun getCurrentByCityName(@Query("q") cityName: String): ResponseBodyCurrent

  @GET("history.json?key=$API_KEY")
  // date: YYYY-MM-dd
  suspend fun getHistoryByCityName(@Query("q") cityName: String, @Query("dt") date: String): ResponseBodyHistory

  @GET("search.json?key=$API_KEY")
  suspend fun search(@Query("q") keyword: String): List<SimpleLocation>

  @GET("forecast.json?key=$API_KEY&&days=10&aqi=no&alerts=no")
  suspend fun getForecastListByLocation(@Query("q") locationName: String): ResponseBodyForecast
}

object WeatherAPIService {
  val retrofitService: WeatherAPI by lazy {
    retrofit.create(WeatherAPI::class.java)
  }
}
