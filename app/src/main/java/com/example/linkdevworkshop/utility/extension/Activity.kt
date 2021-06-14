package com.example.linkdevworkshop.utility.extension

import android.app.Activity
import android.widget.Toast

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun Activity.toast(message:String){
  Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}