package com.example.linkdevworkshop.domain.usecase.news

import com.example.linkdevworkshop.domain.entity.articles.ArticlesEntity
import com.example.linkdevworkshop.domain.repository.articles.ArticlesRepository
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */

class NewsUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {

  operator fun invoke(nextWeb: String, associatedPress: String): Single<Resource<ArticlesEntity>> {
    val nextWebRequest = articlesRepository.loadArticlesFromAPI(nextWeb)
    val associatedPressRequest = articlesRepository.loadArticlesFromAPI(associatedPress)
    val nextWebRequestAssociatedPressRequestZipOperator =
      BiFunction<Resource<ArticlesEntity>, Resource<ArticlesEntity>, Resource<ArticlesEntity>> { nextWebResponse, associatedPressResponse ->
        if (nextWebResponse.state == ERROR && associatedPressResponse.state == ERROR) {
          Resource(
            ERROR,
            message = nextWebResponse.message
          )
        } else {
          val zipOperatorResult = mutableListOf<ArticlesEntity.Article>()
          zipOperatorResult.addAll(nextWebResponse.data?.articles ?: mutableListOf())
          zipOperatorResult.addAll(associatedPressResponse.data?.articles ?: mutableListOf())
          Resource(
            SUCCESS,
            data = ArticlesEntity(zipOperatorResult)
          )
        }
      }
    return Single.zip(
      nextWebRequest,
      associatedPressRequest,
      nextWebRequestAssociatedPressRequestZipOperator
    )

  }
}