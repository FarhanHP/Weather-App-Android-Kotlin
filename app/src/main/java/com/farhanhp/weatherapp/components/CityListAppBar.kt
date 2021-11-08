package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.farhanhp.weatherapp.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton

class CityListAppBar(context: Context, attrs: AttributeSet): AppBarLayout(context, attrs) {
  private val backBtn: MaterialButton
  private val titleTextView: TextView

  init {
    LayoutInflater.from(context).inflate(R.layout.component_city_list_appbar, this, true)

    backBtn = findViewById(R.id.backBtn)
    titleTextView = findViewById(R.id.title)
  }

  fun setBackBtnListener(fn: ()->Unit) {
    backBtn.setOnClickListener{
      fn()
    }
  }

  fun setTitle(title: String) {
    titleTextView.text = title
  }
}