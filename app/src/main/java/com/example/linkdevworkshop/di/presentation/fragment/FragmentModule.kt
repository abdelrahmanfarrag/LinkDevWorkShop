package com.example.linkdevworkshop.di.presentation.fragment

import com.example.linkdevworkshop.data.repository.articles.ArticlesRepositoryImpl
import com.example.linkdevworkshop.di.presentation.scope.PerFragment
import com.example.linkdevworkshop.domain.repository.articles.ArticlesRepository
import dagger.Binds
import dagger.Module

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Module
abstract class FragmentModule {


  @Binds
  @PerFragment
  abstract fun bindsArticlesRepository(repositoryImpl: ArticlesRepositoryImpl): ArticlesRepository
}