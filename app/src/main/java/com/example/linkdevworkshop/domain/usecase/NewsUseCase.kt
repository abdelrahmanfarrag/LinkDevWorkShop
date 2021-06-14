package com.example.linkdevworkshop.domain.usecase

import com.example.linkdevworkshop.domain.entity.ArticlesEntity
import com.example.linkdevworkshop.domain.repository.ArticlesRepository
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
          val combinedNewsList = mutableListOf<ArticlesEntity.Article>()
          combinedNewsList.addAll(nextWebResponse.data?.articles ?: mutableListOf())
          combinedNewsList.addAll(associatedPressResponse.data?.articles ?: mutableListOf())
          Resource(
            SUCCESS,
            data = ArticlesEntity(combinedNewsList)
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