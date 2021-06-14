package com.example.linkdevworkshop.domain.usecase

import com.example.linkdevworkshop.domain.entity.ArticlesEntity
import com.example.linkdevworkshop.domain.repository.ArticlesRepository
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS
import com.example.linkdevworkshop.utility.Error
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */

class NewsUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {

  operator fun invoke(source1: String, source2: String): Single<Resource<ArticlesEntity>> {
    val source1Api = articlesRepository.loadArticlesFromAPI(source1)
    val source2Api = articlesRepository.loadArticlesFromAPI(source2)
    val zipper =
      BiFunction<Resource<ArticlesEntity>, Resource<ArticlesEntity>, Resource<ArticlesEntity>> { dashboardResource, tasksAndActionsResource ->
        if (dashboardResource.state == ERROR || tasksAndActionsResource.state == ERROR) {
          Resource(
            ERROR,
            message = Error.ARTICLE_ERROR
          )
        } else {
          val combinedNewsList = mutableListOf<ArticlesEntity.Article>()
          combinedNewsList.addAll(dashboardResource.data?.articles ?: mutableListOf())
          combinedNewsList.addAll(tasksAndActionsResource.data?.articles ?: mutableListOf())
          Resource(
            SUCCESS,
            data = ArticlesEntity(combinedNewsList)
          )
        }
      }
    return Single.zip(source1Api, source2Api, zipper)

  }
}