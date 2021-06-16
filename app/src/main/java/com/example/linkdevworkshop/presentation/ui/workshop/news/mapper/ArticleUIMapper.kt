package com.example.linkdevworkshop.presentation.ui.workshop.news.mapper

import com.example.linkdevworkshop.domain.entity.articles.ArticlesEntity
import com.example.linkdevworkshop.presentation.ui.workshop.news.mapper.ArticlesUI.ArticleUI

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun ArticlesEntity.mapToArticlesUI() = ArticlesUI(
  articlesUi = articles.map {
    ArticleUI(
      author = it.author,
      description = it.description,
      publishedAt = it.publishedAt,
      title = it.title,
      url = it.url,
      image = it.image
    )
  }
)