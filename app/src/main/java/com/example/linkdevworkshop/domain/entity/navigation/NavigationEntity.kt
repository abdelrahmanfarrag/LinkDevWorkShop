package com.example.linkdevworkshop.domain.entity.navigation

import android.graphics.drawable.Drawable

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
data class NavigationEntity(
  val isSelected: Boolean,
  val title: String,
   val icon: Drawable?
)
