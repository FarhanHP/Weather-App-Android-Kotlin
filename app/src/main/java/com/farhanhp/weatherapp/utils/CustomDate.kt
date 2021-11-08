package com.farhanhp.weatherapp.utils

import java.util.Date

class CustomDate: Date{
  companion object {
    val DAY_NAMES = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    val MONTH_NAMES = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Desember")
  }
  constructor(): super()
  constructor(epoch: Long): super(epoch*1000)

  // YYYY/MM/dd HH:mm:ss
  constructor(string: String): super(string)

  override fun getYear(): Int {
    return super.getYear() + 1900
  }

  private fun timeToString(time: Int): String {
    return if(time < 10) "0$time" else time.toString()
  }

  override fun toLocaleString(): String {
    val hoursMinute = "${timeToString(hours)}:${timeToString(minutes)}"
    val dayDateMonth = toLocaleStringWithoutHourMinute()

    return "$dayDateMonth - $hoursMinute"
  }

  fun toLocaleStringWithoutHourMinute(): String {
    val now = CustomDate()
    val dayDateMonth = "${DAY_NAMES[day]}, $date ${MONTH_NAMES[month]}"

    val output = (
      if(now.year == year) {
        "$dayDateMonth"
      } else {
        "$dayDateMonth $year"
      }
      )

    return output
  }

  fun toYYYYMMdd(): String = "$year-${timeToString(month+1)}-${timeToString(date)}"

  fun toYYYYMMdd2(): String = "$year/${timeToString(month+1)}/${timeToString(date)}"
}