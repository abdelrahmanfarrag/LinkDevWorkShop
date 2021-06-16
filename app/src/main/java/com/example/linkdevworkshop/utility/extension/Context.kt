package com.example.linkdevworkshop.utility.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Suppress("DEPRECATION") fun Context.isNetworkAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
      else -> false
    }
  } else {
    val nwInfo = connectivityManager.activeNetworkInfo ?: return false
    return nwInfo.isConnected
  }
}