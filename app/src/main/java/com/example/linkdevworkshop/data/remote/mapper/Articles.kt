package com.example.linkdevworkshop.data.remote.mapper

import com.example.linkdevworkshop.data.remote.response.ArticlesResponse
import com.example.linkdevworkshop.domain.entity.ArticlesEntity
import com.example.linkdevworkshop.domain.entity.ArticlesEntity.Article

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun ArticlesResponse.mapToArticlesEntity() = ArticlesEntity(
  articles = this.articles.map {
    Article(
      author = it.author?:"",
      description = it.description?:"",
      publishedAt = it.publishedAt?:"",
      title = it.title?:"",
      url = it.url?:"",
      image = it.urlToImage?:""
    )
  }
)