package com.example.linkdevworkshop.presentation.workshop.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.data.remote.api.EndPoints.Query.ASSOCIATED_PRESS
import com.example.linkdevworkshop.data.remote.api.EndPoints.Query.THE_NEXT_WEB
import com.example.linkdevworkshop.data.remote.network.NetworkInterceptor
import com.example.linkdevworkshop.domain.usecase.news.NewsUseCase
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.workshop.news.mapper.ArticlesUI
import com.example.linkdevworkshop.presentation.workshop.news.mapper.mapToArticlesUI
import com.example.linkdevworkshop.utility.Error.Code
import com.example.linkdevworkshop.utility.Error.GENERAL
import com.example.linkdevworkshop.utility.Error.NETWORK
import com.example.linkdevworkshop.utility.extension.setError
import com.example.linkdevworkshop.utility.extension.setLoading
import com.example.linkdevworkshop.utility.extension.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  private val _articles = MutableLiveData<Resource<ArticlesUI>>()

  val articles: LiveData<Resource<ArticlesUI>>
    get() = _articles

  fun getCombinedNewsArticle() {
    if (articles.value == null) {
      compositeDisposable.add(newsUseCase(THE_NEXT_WEB, ASSOCIATED_PRESS)
        .doOnSubscribe { _articles.setLoading() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ movieEntityResource ->
          movieEntityResource.data?.let {
            _articles.setSuccess(it.mapToArticlesUI())
          } ?: _articles.setError(movieEntityResource?.message ?: GENERAL)
        }, { throwable ->
          when (throwable.message) {
            NetworkInterceptor.NETWORK_ISSUE -> _articles.setError(NETWORK)
            Code.MAX_REQUESTS_COUNT_REACHED -> _articles.setError(Code.MAX_REQUESTS_COUNT_REACHED)
            else -> _articles.setError(GENERAL)
          }
        })
      )
    }
  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}