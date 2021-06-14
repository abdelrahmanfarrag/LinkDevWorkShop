package com.example.linkdevworkshop.data.remote.api

import com.example.linkdevworkshop.data.remote.api.EndPoints.ARTICLES
import com.example.linkdevworkshop.data.remote.api.EndPoints.Query.SOURCE
import com.example.linkdevworkshop.data.remote.response.ArticlesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
interface Api {
  @GET(ARTICLES)
  fun loadArticles(@Query(SOURCE) source: String): Single<Response<ArticlesResponse>>
}