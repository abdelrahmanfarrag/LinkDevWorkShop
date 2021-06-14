package com.example.linkdevworkshop.utility.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.linkdevworkshop.R

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun ImageView.load(url: String) {
  Glide.with(context.applicationContext)
    .load(url)
    .placeholder(R.drawable.ic_placeholder)
    .error(R.drawable.ic_placeholder)
    .into(this)
}
