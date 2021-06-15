package com.example.linkdevworkshop.data.locale.mapper

import com.example.linkdevworkshop.data.locale.model.NavigationModel
import com.example.linkdevworkshop.domain.entity.navigation.NavigationEntity

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun NavigationModel.mapToNavigationEntity() = NavigationEntity(
  title = title,
  icon = icon,
  isSelected = isSelected
)