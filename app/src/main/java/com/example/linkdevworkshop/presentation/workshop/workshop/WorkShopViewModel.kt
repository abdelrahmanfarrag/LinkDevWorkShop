package com.example.linkdevworkshop.presentation.workshop.workshop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.domain.usecase.navigation.NavigationUseCase
import com.example.linkdevworkshop.presentation.workshop.workshop.mapper.NavigationModelUI
import com.example.linkdevworkshop.presentation.workshop.workshop.mapper.mapToNavigationModelUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
class WorkShopViewModel @Inject constructor(private val navigationUseCase: NavigationUseCase) :
  ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  private val _navigationList = MutableLiveData<List<NavigationModelUI>>()

  val navigationList: LiveData<List<NavigationModelUI>>
    get() = _navigationList

  fun getNavigationMenu() {
    compositeDisposable.add(
      navigationUseCase.invoke()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ navigationEntityList ->
          _navigationList.postValue(navigationEntityList.map { navigationEntity ->
            navigationEntity.mapToNavigationModelUI()
          })
        }, {
          Log.d("exception", it.toString())
        })
    )
  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}