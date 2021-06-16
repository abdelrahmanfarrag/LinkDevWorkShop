package com.example.linkdevworkshop.presentation.ui.workshop.workshop.adapter

import com.example.linkdevworkshop.databinding.ItemNavigationViewBinding
import com.example.linkdevworkshop.presentation.ui.base.BaseViewHolder
import com.example.linkdevworkshop.presentation.ui.workshop.workshop.mapper.NavigationModelUI
import com.example.linkdevworkshop.utility.extension.visible

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
class NavigationMenuViewHolder(
  private val itemNavigationViewBinding: ItemNavigationViewBinding,
  private val onItemSelected: (NavigationModelUI) -> Unit
) : BaseViewHolder<NavigationModelUI>(itemNavigationViewBinding) {

  override fun bind(t: NavigationModelUI, isLast: Boolean) {
    itemView.setOnClickListener {
      onItemSelected.invoke(t)
    }
    itemNavigationViewBinding.apply {
      navigationItemTitleTextView.text = t.title
      navigationItemIconImageView.setImageDrawable(t.icon)
      if (t.isSelected) navigationSelectedIndicatorView.visible()
    }
  }
}