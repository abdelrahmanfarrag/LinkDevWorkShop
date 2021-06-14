package com.example.linkdevworkshop.di.application.module.network

import com.example.linkdevworkshop.BuildConfig
import com.example.linkdevworkshop.data.remote.converter.NullOnEmptyConverterFactory
import com.example.linkdevworkshop.di.application.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Module(includes = [OkHttpModule::class])
abstract class NetworkModule {
  companion object {
    @Provides
    @ApplicationScope fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @ApplicationScope fun providesRetrofitInstance(
      client: OkHttpClient,
      gson: Gson
    ): Retrofit = Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(NullOnEmptyConverterFactory())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(client)
      .build()
  }
}