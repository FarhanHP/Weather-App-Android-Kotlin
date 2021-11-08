package com.farhanhp.weatherapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.farhanhp.weatherapp.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton

class CityDetailAppBar(context: Context, attrs: AttributeSet): AppBarLayout(context, attrs) {
  private val cityNameTextView: TextView
  private val dateTextView: TextView
  private val backBtn: MaterialButton
  private val searchBtn: MaterialButton
  private val closeSearchBtn: MaterialButton
  private val searchEditText: EditText
  private val normalAppBar: ConstraintLayout
  private val searchBarContainer: LinearLayout

  init {
    LayoutInflater.from(context).inflate(R.layout.component_city_detail_appbar, this, true)

    cityNameTextView = findViewById(R.id.cityName)
    dateTextView = findViewById(R.id.date)
    backBtn = findViewById(R.id.backBtn)
    searchBtn = findViewById(R.id.searchBtn)
    closeSearchBtn = findViewById(R.id.closeSearchBtn)
    searchEditText = findViewById(R.id.searchInput)
    normalAppBar = findViewById(R.id.normalAppbar)
    searchBarContainer = findViewById(R.id.searchBarContainer)

    context.theme.obtainStyledAttributes(attrs, R.styleable.CityDetailAppBar, 0, 0).apply {
      try {
        val location = getString(R.styleable.CityDetailAppBar_location)
        val date = getString(R.styleable.CityDetailAppBar_date)
        val isHome = getBoolean(R.styleable.CityDetailAppBar_isHome, false)

        setIsHome(isHome)

        if(location != null) {
          setLocation(location)
        }

        if(date != null) {
          setDate(date)
        }
      } finally {
        recycle()
      }
    }

    searchBtn.setOnClickListener{
      onSearchBtnClick()
    }

    closeSearchBtn.setOnClickListener{
      onCloseSearchBtnClick()
    }
  }

  private fun onSearchBtnClick() {
    normalAppBar.visibility = View.GONE
    searchBarContainer.visibility = View.VISIBLE
  }

  private fun onCloseSearchBtnClick() {
    normalAppBar.visibility = View.VISIBLE
    searchBarContainer.visibility = View.GONE

    // close keyboard
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
  }

  fun setLocation(location: String) {
    cityNameTextView.text = location
  }

  fun setDate(date: String) {
    dateTextView.text = date
  }

  fun setIsHome(isHome: Boolean) {
    backBtn.visibility = when(isHome) {
      true -> View.GONE
      else -> View.VISIBLE
    }
  }

  fun setBackBtnListener(fn: ()->Unit) {
    backBtn.setOnClickListener{
      fn()
    }
  }

  fun setSearchHandler(fn: (keyword: String)->Unit) {
    searchEditText.setOnKeyListener(OnKeyListener { _, keyCode, event ->
      val value = searchEditText.text.toString()
      if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP && value.isNotEmpty()) {
        fn(value)
        return@OnKeyListener true
      }
      false
    })
  }
}