package com.example.linkdevworkshop.di.application.module.network

import android.app.Application
import android.util.Log
import com.example.linkdevworkshop.BuildConfig
import com.example.linkdevworkshop.data.remote.api.EndPoints.Query.API_KEY
import com.example.linkdevworkshop.data.remote.network.NetworkInterceptor
import com.example.linkdevworkshop.di.application.scope.ApplicationScope
import com.example.linkdevworkshop.utility.extension.isNetworkAvailable
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit.SECONDS


/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
@Module
abstract class OkHttpModule {
  companion object {
    @Provides
    @ApplicationScope fun providesGeneralOkHttpClient(
      loggingInterceptor: HttpLoggingInterceptor,
      networkInterceptor: NetworkInterceptor,
    ): OkHttpClient {
      return getOkHttpClientBuilder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
    }


    @Provides
    @ApplicationScope fun getNetworkInterceptor(application: Application): NetworkInterceptor =
      object : NetworkInterceptor() {
        override fun isInternetAvailable(): Boolean = application.isNetworkAvailable()
      }

    @Provides
    @ApplicationScope fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
      val logging = HttpLoggingInterceptor()
      logging.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
      return logging
    }

    private fun getOkHttpClientBuilder(): Builder =
      Builder().connectTimeout(20, SECONDS)
        .readTimeout(30, SECONDS)
        .writeTimeout(20, SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor { interceptSocketException(it) }

    @Throws(IOException::class) private fun interceptSocketException(chain: Chain): Response {
      var request: Request = chain.request()
      val url: HttpUrl = request.url
        .newBuilder()
        .addQueryParameter(API_KEY, BuildConfig.API_KEY)
        .build()
      request = request
        .newBuilder()
        .url(url)
        .build()
      val response = chain.proceed(request)
      try {
        return response.newBuilder()
          .body(response.body?.string()?.toResponseBody(response.body?.contentType()))
          .build()
      } catch (exception: SocketTimeoutException) {
        Log.e("exception", exception.toString())
      }
      return response
    }
  }
}