package com.example.linkdevworkshop.presentation.ui.workshop.workshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.viewbinding.ViewBinding
import com.example.linkdevworkshop.databinding.ItemNavigationViewBinding
import com.example.linkdevworkshop.presentation.ui.base.RecyclerAdapter
import com.example.linkdevworkshop.presentation.ui.workshop.workshop.mapper.NavigationModelUI
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
class NavigationMenuAdapter @Inject constructor() :
  RecyclerAdapter<NavigationModelUI, NavigationMenuViewHolder>() {

  private lateinit var onItemClicked: (NavigationModelUI) -> Unit

  override fun instantiateViewHolder(
    itemView: ViewBinding,
    viewType: Int
  ) = NavigationMenuViewHolder(itemView as ItemNavigationViewBinding, onItemClicked)

  override fun generateAsyncDifferCallback(): ItemCallback<NavigationModelUI> {
    return object : DiffUtil.ItemCallback<NavigationModelUI>() {
      override fun areItemsTheSame(
        oldItem: NavigationModelUI,
        newItem: NavigationModelUI
      ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
      }

      override fun areContentsTheSame(
        oldItem: NavigationModelUI,
        newItem: NavigationModelUI
      ): Boolean {
        return oldItem == newItem
      }
    }
  }

  override fun generatedBindingLayout(
    inflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean
  ) = ItemNavigationViewBinding.inflate(inflater, parent, attachToRoot)

  override fun setItems(items: List<NavigationModelUI>) {
    differAsync.submitList(items)
  }

  override fun onBindViewHolder(holder: NavigationMenuViewHolder, position: Int) {
    holder.bind(differAsync.currentList[position])
  }

  fun setOnItemClicked(onItemClicked: (NavigationModelUI) -> Unit) {
    this.onItemClicked = onItemClicked
  }

}