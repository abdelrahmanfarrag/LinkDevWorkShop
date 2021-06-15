package com.example.linkdevworkshop.presentation.workshop.workshop.mapper

import android.graphics.drawable.Drawable

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
data class NavigationModelUI(
  val isSelected: Boolean = false,
  val title: String,
  val icon: Drawable?
)