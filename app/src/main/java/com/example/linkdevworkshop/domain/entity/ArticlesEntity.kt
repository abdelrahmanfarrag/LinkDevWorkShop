package com.example.linkdevworkshop.domain.entity

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
data class ArticlesEntity(
  val articles: List<Article>
) {

  data class Article(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val image: String
  )
}