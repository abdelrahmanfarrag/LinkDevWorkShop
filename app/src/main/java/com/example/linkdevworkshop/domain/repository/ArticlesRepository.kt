package com.example.linkdevworkshop.domain.repository

import com.example.linkdevworkshop.domain.entity.ArticlesEntity
import com.example.linkdevworkshop.presentation.common.Resource
import io.reactivex.Single

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
interface ArticlesRepository {
  fun loadArticlesFromAPI(source: String): Single<Resource<ArticlesEntity>>
}