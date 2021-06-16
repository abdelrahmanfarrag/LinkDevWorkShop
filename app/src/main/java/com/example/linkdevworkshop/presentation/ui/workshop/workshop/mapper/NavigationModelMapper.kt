package com.example.linkdevworkshop.presentation.ui.workshop.workshop.mapper

import com.example.linkdevworkshop.domain.entity.navigation.NavigationEntity

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */

fun NavigationEntity.mapToNavigationModelUI() = NavigationModelUI(
  title = title,
  icon = icon,
  isSelected = isSelected
)