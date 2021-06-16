package com.example.linkdevworkshop.di.presentation.activity

import com.example.linkdevworkshop.data.repository.navigation.NavigationRepositoryImpl
import com.example.linkdevworkshop.di.presentation.scope.PerActivity
import com.example.linkdevworkshop.domain.repository.navigation.NavigationRepository
import dagger.Binds
import dagger.Module

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
@Module
abstract class ActivityModule {

  @Binds
  @PerActivity
  abstract fun bindsArticlesRepository(navigationRepositoryImpl: NavigationRepositoryImpl): NavigationRepository

}