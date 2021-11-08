package com.farhanhp.weatherapp.utils

import android.content.res.Resources

class Graphic {
  companion object {
    fun toDp(number: Int): Int {
      return (number * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
  }
}