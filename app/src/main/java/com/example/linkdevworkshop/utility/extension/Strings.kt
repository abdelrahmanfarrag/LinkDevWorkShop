package com.example.linkdevworkshop.utility.extension

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun String.convertDateToPattern(convertToPattern: String = "MMM dd, yyyy"): String {
  Log.d("printTHis", this)
  return try {
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
    val date = format.parse(this)
   // val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val dateFormat = SimpleDateFormat(convertToPattern, Locale.getDefault())
    dateFormat.format(date)
  } catch (ex: Exception) {
    ex.printStackTrace()
    ""
  }
}
fun String.parseTodaysDate(): String? {
  val inputPattern = "yyyy-MM-dd'T'HH:mm:ssZ"
  val outputPattern = "dd-MM-yyyy"
  val inputFormat = SimpleDateFormat(inputPattern)
  val outputFormat = SimpleDateFormat(outputPattern)
  var date: Date? = null
  var str: String? = null
  try {
    date = inputFormat.parse(this)
    str = outputFormat.format(date)
    Log.i("mini", "Converted Date Today:$str")
  } catch (e: ParseException) {
    e.printStackTrace()
  }
  return str
}