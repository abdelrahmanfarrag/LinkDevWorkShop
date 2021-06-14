package com.example.linkdevworkshop.presentation.workshop.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.viewbinding.ViewBinding
import com.example.linkdevworkshop.databinding.ItemNewsBinding
import com.example.linkdevworkshop.presentation.base.RecyclerAdapter
import com.example.linkdevworkshop.presentation.workshop.news.mapper.ArticlesUI.ArticleUI
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NewsAdapter @Inject constructor() : RecyclerAdapter<ArticleUI, NewsViewHolder>() {

  private lateinit var onNewsClicked: (ArticleUI) -> Unit
  override fun instantiateViewHolder(itemView: ViewBinding, viewType: Int) =
    NewsViewHolder(itemView as ItemNewsBinding, onNewsClicked)

  override fun generateAsyncDifferCallback(): ItemCallback<ArticleUI> {
    return object : DiffUtil.ItemCallback<ArticleUI>() {
      override fun areItemsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
      }

      override fun areContentsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean {
        return oldItem == newItem
      }
    }
  }

  override fun generatedBindingLayout(
    inflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean
  ) = ItemNewsBinding.inflate(inflater, parent, attachToRoot)

  override fun setItems(items: List<ArticleUI>) {
    differAsync.submitList(items)
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    holder.bind(differAsync.currentList[position])
  }

  fun setOnArticleClicked(onNewsClicked: (ArticleUI) -> Unit) {
    this.onNewsClicked = onNewsClicked
  }
}