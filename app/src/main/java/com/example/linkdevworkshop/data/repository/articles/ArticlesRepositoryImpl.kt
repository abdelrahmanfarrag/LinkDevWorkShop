package com.example.linkdevworkshop.data.repository.articles

import com.example.linkdevworkshop.data.remote.api.Api
import com.example.linkdevworkshop.data.remote.mapper.mapToArticlesEntity
import com.example.linkdevworkshop.data.remote.validator.Validator
import com.example.linkdevworkshop.domain.entity.articles.ArticlesEntity
import com.example.linkdevworkshop.domain.repository.articles.ArticlesRepository
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class ArticlesRepositoryImpl @Inject constructor(
  private val validator: Validator,
  private val api: Api
) : ArticlesRepository {

  override fun loadArticlesFromAPI(source: String): Single<Resource<ArticlesEntity>> {
    return api.loadArticles(source)
      .map { validator.validateApiResponse(it) }
      .map { articles ->
        articles.data?.let { articleResponse ->
          Resource(SUCCESS, data = articleResponse.mapToArticlesEntity())
        } ?: Resource(ERROR, message = articles.message)
      }
  }
}