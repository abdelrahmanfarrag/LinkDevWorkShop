package com.example.linkdevworkshop.presentation.workshop.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.data.remote.network.NetworkInterceptor
import com.example.linkdevworkshop.domain.usecase.NewsUseCase
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.workshop.news.mapper.ArticlesUI
import com.example.linkdevworkshop.presentation.workshop.news.mapper.mapToArticlesUI
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

  fun getCombinedNewsArticle(){
    compositeDisposable.add(newsUseCase("the-next-web","associated-press")
      .doOnSubscribe { _articles.setLoading() }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ movieEntityResource ->
        movieEntityResource.data?.let {
          _articles.setSuccess(it.mapToArticlesUI())
        } ?: _articles.setError(GENERAL)

      }, { throwable ->
        Log.d("throwable",throwable.toString())
        if (throwable.message == NetworkInterceptor.NETWORK_ISSUE)
          _articles.setError(NETWORK)
        else
          _articles.setError(GENERAL)
      })
    )  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}