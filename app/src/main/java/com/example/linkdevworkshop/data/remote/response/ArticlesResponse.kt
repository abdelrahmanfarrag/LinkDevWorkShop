package com.example.linkdevworkshop.data.remote.response

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
  @SerializedName("articles")
  val articles: List<Article>,
  @SerializedName("sortBy")
  val sortBy: String,
  @SerializedName("source")
  val source: String,
  @SerializedName("status")
  val status: String
) {
  data class Article(
    @SerializedName("author")
    val author: String?=null,
    @SerializedName("description")
    val description: String?=null,
    @SerializedName("publishedAt")
    val publishedAt: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null,
    @SerializedName("urlToImage")
    val urlToImage: String?=null
  )
}