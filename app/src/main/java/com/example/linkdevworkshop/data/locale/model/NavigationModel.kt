package com.example.linkdevworkshop.data.locale.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
data class NavigationModel(
  val isSelected: Boolean = false,
  val title: String,
  val icon: Drawable?
)
