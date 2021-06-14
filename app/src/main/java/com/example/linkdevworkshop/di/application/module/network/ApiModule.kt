package com.example.linkdevworkshop.di.application.module.network

import com.example.linkdevworkshop.data.remote.api.Api
import com.example.linkdevworkshop.di.application.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Module(includes = [NetworkModule::class])
abstract class ApiModule {
  companion object {
    @Provides
    @ApplicationScope fun providesApi(
      retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)
  }
}