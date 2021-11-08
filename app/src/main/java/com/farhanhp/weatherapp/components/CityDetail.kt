package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import com.farhanhp.weatherapp.R
import com.bumptech.glide.Glide
import com.farhanhp.weatherapp.retrofit.datas.Hourly
import com.farhanhp.weatherapp.utils.CustomDate
import com.google.android.material.button.MaterialButton
import kotlin.math.roundToInt

class CityDetail(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs) {
  private val appBar: CityDetailAppBar

  private val temperatureTextView: TextView
  private val weatherConditionTextTextView: TextView
  private val weatherConditionImageImageView: ImageView
  private val lastUpdatedTextView: TextView

  private val windKphWeatherPropertyCard: WeatherPropertyCard
  private val pressureMbWeatherPropertyCard: WeatherPropertyCard
  private val precipMmWeatherPropertyCard: WeatherPropertyCard
  private val humidityWeatherPropertyCard: WeatherPropertyCard
  private val cloudWeatherPropertyCard: WeatherPropertyCard
  private val gustKphWeatherPropertyCard: WeatherPropertyCard

  private val epaIndexWeatherPropertyCard: WeatherPropertyCard
  private val defraIndexWeatherPropertyCard: WeatherPropertyCard
  private val coWeatherPropertyCard: WeatherPropertyCard
  private val o3WeatherPropertyCard: WeatherPropertyCard
  private val no2WeatherPropertyCard: WeatherPropertyCard
  private val so2WeatherPropertyCard: WeatherPropertyCard
  private val pm25WeatherPropertyCard: WeatherPropertyCard
  private val pm10WeatherPropertyCard: WeatherPropertyCard

  private val hourlyWeatherHistoryCardContainer: LinearLayout
  private val weatherForecastBtn: MaterialButton

  private var localDate = CustomDate()

  init {
    LayoutInflater.from(context).inflate(R.layout.component_city_detail, this, true)

    appBar = findViewById(R.id.cityDetailComponent_appbar)

    weatherConditionTextTextView = findViewById(R.id.weatherConditionText)
    weatherConditionImageImageView = findViewById(R.id.weatherConditionImage)
    lastUpdatedTextView = findViewById(R.id.lastUpdated)

    temperatureTextView = findViewById(R.id.temperature)
    windKphWeatherPropertyCard = findViewById(R.id.windKph)
    pressureMbWeatherPropertyCard = findViewById(R.id.pressureMb)
    precipMmWeatherPropertyCard = findViewById(R.id.precipMm)
    humidityWeatherPropertyCard = findViewById(R.id.humidity)
    cloudWeatherPropertyCard = findViewById(R.id.cloud)
    gustKphWeatherPropertyCard = findViewById(R.id.gustKph)

    epaIndexWeatherPropertyCard = findViewById(R.id.epaIndex)
    defraIndexWeatherPropertyCard = findViewById(R.id.defraIndex)
    coWeatherPropertyCard = findViewById(R.id.co)
    o3WeatherPropertyCard = findViewById(R.id.o3)
    no2WeatherPropertyCard = findViewById(R.id.no2)
    so2WeatherPropertyCard = findViewById(R.id.so2)
    pm25WeatherPropertyCard = findViewById(R.id.pm25)
    pm10WeatherPropertyCard = findViewById(R.id.pm10)

    hourlyWeatherHistoryCardContainer = findViewById(R.id.hourlyWeatherHistoryCardContainer)
    weatherForecastBtn = findViewById(R.id.weatherForecastBtn)
  }

  fun setTemperature(tempC: Double) {
    temperatureTextView.text = "$tempC\u2103"
  }

  fun setWindKph(windKph: Double) {
    windKphWeatherPropertyCard.setValue("$windKph KM/Hour")
  }

  fun setPressureMb(pressureMb: Double) {
    pressureMbWeatherPropertyCard.setValue("$pressureMb millibars")
  }

  fun setPrecipMm(precipMm: Double) {
    precipMmWeatherPropertyCard.setValue("$precipMm millimeters")
  }

  fun setHumidity(humidity: Int) {
    humidityWeatherPropertyCard.setValue("$humidity%")
  }

  fun setCloud(cloud: Int) {
    cloudWeatherPropertyCard.setValue("$cloud %")
  }

  fun setGustKph(gustKph: Double) {
    gustKphWeatherPropertyCard.setValue("$gustKph KM/Hour")
  }

  fun setWeatherConditionText(weatherConditionText: String) {
    weatherConditionTextTextView.text = weatherConditionText
  }

  fun setLastUpdated(lastUpdated: String) {
    lastUpdatedTextView.text = "Last Updated $lastUpdated (Local Time)"
  }

  fun setLocation(location: String) {
    appBar.setLocation(location)
  }

  fun setLocalDate(localDate: CustomDate) {
    this.localDate = localDate
    appBar.setDate(localDate.toLocaleString())
  }

  fun setIsHome(isHome: Boolean) {
    appBar.setIsHome(isHome)
  }

  fun setBackBtnListener(fn: ()->Unit) {
    appBar.setBackBtnListener(fn)
  }

  fun setWeatherConditionImage(imageUrl: String) {
    val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(weatherConditionImageImageView.context).load(imageUri).into(weatherConditionImageImageView)
  }

  fun setEpaIndex(epaIndex: Int) {
    val description = when(epaIndex) {
      1 -> "Good"
      2 -> "Moderate"
      3 -> "Unhealthy for sensitive group"
      4 -> "Unhealthy"
      5 -> "Very Unhealthy"
      else -> "Hazardous"
    }

    epaIndexWeatherPropertyCard.setTitle("EPA Index: $epaIndex")
    epaIndexWeatherPropertyCard.setValue("$description")
  }

  fun setDefraIndex(defraIndex: Int) {
    var description = "Pollution"
    description = if(defraIndex >= 10) {
      "Very High $description"
    } else if(defraIndex >= 7) {
      "High $description"
    } else if(defraIndex >= 4) {
      "Medium $description"
    } else {
      "Low $description"
    }

    defraIndexWeatherPropertyCard.setTitle("Defra Index: $defraIndex")
    defraIndexWeatherPropertyCard.setValue("$description")
  }

  private fun round2AfterComma(value: Double): Double {
    return (value * 100).roundToInt().toDouble() / 100
  }

  fun setCo(co: Double) {
    coWeatherPropertyCard.setValue("${round2AfterComma(co)} μg/m3")
  }

  fun setO3(o3: Double) {
    o3WeatherPropertyCard.setValue("${round2AfterComma(o3)} μg/m3")
  }

  fun setNo2(no2: Double) {
    no2WeatherPropertyCard.setValue("${round2AfterComma(no2)} μg/m3")
  }

  fun setSo2(so2: Double) {
    so2WeatherPropertyCard.setValue("${round2AfterComma(so2)} μg/m3")
  }

  fun setPm25(pm25: Double) {
    pm25WeatherPropertyCard.setValue("${round2AfterComma(pm25)} μg/m3")
  }

  fun setPm10(pm10: Double) {
    pm10WeatherPropertyCard.setValue("${round2AfterComma(pm10)} μg/m3")
  }

  fun setWeatherHourlyHistory(list: List<Hourly>) {
    val nowHour = localDate.hours

    for(i in list.indices) {
      val currentHourly = list[i]
      val currentHour = CustomDate(currentHourly.time.replace("-", "/")).hours
      val child = HourlyWeatherHistoryCard(context, attrs)
      hourlyWeatherHistoryCardContainer.addView(child)
      child.layoutParams = LayoutParams(
        0,
        ViewGroup.LayoutParams.MATCH_PARENT,
        1.0f
      )

      child.setImage(currentHourly.condition.icon)
      child.setTemperature("${currentHourly.tempC}℃")

      if(nowHour == currentHour) {
        child.setIsActive(true)
        child.setTime("Now")
      } else {
        child.setIsActive(false)
        child.setTime("${currentHour}:00")
      }
    }
  }

  fun setSearchHandler(fn: (keyword: String) -> Unit) {
    appBar.setSearchHandler(fn)
  }

  fun setOnWeatherForecastBtnClick(fn: ()->Unit) {
    weatherForecastBtn.setOnClickListener{
      fn()
    }
  }
}