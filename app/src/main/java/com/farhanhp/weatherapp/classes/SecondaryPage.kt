package com.farhanhp.weatherapp.classes

import androidx.fragment.app.Fragment

abstract class SecondaryPage: Fragment() {
  protected fun openPriorPage() {
    activity?.onBackPressed()
  }
}