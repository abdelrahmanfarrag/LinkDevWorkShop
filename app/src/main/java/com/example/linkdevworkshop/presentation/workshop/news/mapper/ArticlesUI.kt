package com.example.linkdevworkshop.presentation.workshop.news.mapper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Parcelize
data class ArticlesUI(
  val articlesUi: List<ArticleUI>
) : Parcelable {

  @Parcelize
  data class ArticleUI(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val image: String
  ) : Parcelable
}