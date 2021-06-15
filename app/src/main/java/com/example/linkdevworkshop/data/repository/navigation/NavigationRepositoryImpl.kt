package com.example.linkdevworkshop.data.repository.navigation

import android.app.Application
import androidx.core.content.ContextCompat
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.data.locale.mapper.mapToNavigationEntity
import com.example.linkdevworkshop.data.locale.model.NavigationModel
import com.example.linkdevworkshop.domain.entity.navigation.NavigationEntity
import com.example.linkdevworkshop.domain.repository.navigation.NavigationRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
class NavigationRepositoryImpl @Inject constructor(private val context: Application) :
  NavigationRepository {

  override fun getNavigationList(): Single<List<NavigationEntity>> {
    val navigationItems = mutableListOf<NavigationModel>()
    navigationItems.add(
      NavigationModel(
        title = context.getString(R.string.explore),
        icon = ContextCompat.getDrawable(context, R.drawable.ic_explore)
      )
    )
    navigationItems.add(
      NavigationModel(
        title = context.getString(R.string.live_chat),
        icon = ContextCompat.getDrawable(context, R.drawable.ic_live)
      )
    )
    navigationItems.add(
      NavigationModel(
        title = context.getString(R.string.gallery),
        icon = ContextCompat.getDrawable(context, R.drawable.ic_gallery)
      )
    )
    navigationItems.add(
      NavigationModel(
        title = context.getString(R.string.wish_list),
        icon = ContextCompat.getDrawable(context, R.drawable.ic_wishlist)
      )
    )
    navigationItems.add(
      NavigationModel(
        title = context.getString(R.string.e_magazine),
        icon = ContextCompat.getDrawable(context, R.drawable.ic_e_magazine)
      )
    )
    return Single.just(navigationItems.map {
      it.mapToNavigationEntity()
    })
  }
}