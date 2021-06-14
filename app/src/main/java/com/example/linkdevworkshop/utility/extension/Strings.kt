package com.example.linkdevworkshop.utility.extension

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun String.convertDateToPattern(convertToPattern: String = "MMM dd, yyyy"): String {
  Log.d("printTHis", this)
  return try {
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat(convertToPattern, Locale.ENGLISH)
    val date = format.parse(this)
    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    dateFormat.format(date)
  } catch (ex: Exception) {
    ex.printStackTrace()
    ""
  }
}