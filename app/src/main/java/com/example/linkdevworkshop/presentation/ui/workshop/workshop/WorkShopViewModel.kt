package com.example.linkdevworkshop.presentation.ui.workshop.workshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.domain.usecase.navigation.NavigationUseCase
import com.example.linkdevworkshop.presentation.ui.workshop.workshop.mapper.NavigationModelUI
import com.example.linkdevworkshop.presentation.ui.workshop.workshop.mapper.mapToNavigationModelUI
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
    if (navigationList.value == null) {
      compositeDisposable.add(
        navigationUseCase()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ navigationEntityList ->
            _navigationList.value = navigationEntityList.map { navigationEntity ->
              navigationEntity.mapToNavigationModelUI()
            }
          }, {})
      )
    }
  }

  fun updateSelectedNavigationMenu(item: NavigationModelUI) {
    item.isSelected = !item.isSelected
    val navigationMenuItems = navigationList.value
    navigationMenuItems?.let { navigationItems ->
      navigationItems.find { it.title == item.title }?.let {
        val updatedItem = it.copy(
          title = item.title,
          isSelected = item.isSelected,
          icon = item.icon
        )
        _navigationList.value = navigationMenuItems.map { oldItem ->
          if (oldItem.title == item.title) {
            updatedItem
          } else {
            oldItem.copy(isSelected = false, title = oldItem.title, icon = oldItem.icon)
          }
        }
      }
    }
  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}