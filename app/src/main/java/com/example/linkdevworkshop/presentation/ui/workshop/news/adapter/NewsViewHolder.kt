package com.example.linkdevworkshop.presentation.ui.workshop.news.adapter

import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.ItemNewsBinding
import com.example.linkdevworkshop.presentation.ui.base.BaseViewHolder
import com.example.linkdevworkshop.presentation.ui.workshop.news.mapper.ArticlesUI.ArticleUI
import com.example.linkdevworkshop.utility.extension.convertDateToPattern
import com.example.linkdevworkshop.utility.extension.load

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NewsViewHolder(
  private val itemNewsBinding: ItemNewsBinding,
  private val onNewsClicked: (ArticleUI) -> Unit
) : BaseViewHolder<ArticleUI>(itemNewsBinding) {
  override fun bind(t: ArticleUI, isLast: Boolean) {
    itemView.setOnClickListener {
      onNewsClicked.invoke(t)
    }
    itemNewsBinding.apply {
      newsAuthorTextView.text =
        String.format("%s %s", itemView.context.getString(R.string.by), t.author)
      newsTitleTextView.text = t.title
      newsDateTextView.text = t.publishedAt.convertDateToPattern()
      newsImageView.load(t.image)
    }
  }
}