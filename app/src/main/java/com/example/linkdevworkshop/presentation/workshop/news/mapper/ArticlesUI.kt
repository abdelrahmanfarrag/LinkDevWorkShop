package com.example.linkdevworkshop.presentation.workshop.news.mapper

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
data class ArticlesUI(
  val articlesUi: List<ArticleUI>
) {

  data class ArticleUI(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val image: String
  )
}