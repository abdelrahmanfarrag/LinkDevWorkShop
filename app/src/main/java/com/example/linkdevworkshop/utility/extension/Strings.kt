package com.example.linkdevworkshop.utility.extension

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun String.convertDateToPattern(convertToPattern: String = "MMM dd, yyyy"): String {
  return try {
    val resource = this.let {
      replace("T", " ", true).replace("Z", "", true)
    }
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val date = format.parse(resource)
    val dateFormat = SimpleDateFormat(convertToPattern, Locale.getDefault())
    dateFormat.format(date!!)
  } catch (ex: Exception) {
    ex.printStackTrace()
    ""
  }
}
