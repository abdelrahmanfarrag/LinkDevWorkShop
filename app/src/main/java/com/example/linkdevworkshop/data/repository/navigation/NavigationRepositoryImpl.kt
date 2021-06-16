package com.example.linkdevworkshop.data.repository.navigation

import android.app.Application
import androidx.core.content.ContextCompat
import com.example.linkdevworkshop.R.drawable
import com.example.linkdevworkshop.R.string
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
    val navigationItems = mutableListOf<NavigationEntity>()
    navigationItems.add(
      NavigationEntity(
        title = context.getString(string.explore),
        icon = ContextCompat.getDrawable(context, drawable.ic_explore)
      )
    )
    navigationItems.add(
      NavigationEntity(
        title = context.getString(string.live_chat),
        icon = ContextCompat.getDrawable(context, drawable.ic_live)
      )
    )
    navigationItems.add(
      NavigationEntity(
        title = context.getString(string.gallery),
        icon = ContextCompat.getDrawable(context, drawable.ic_gallery)
      )
    )
    navigationItems.add(
      NavigationEntity(
        title = context.getString(string.wish_list),
        icon = ContextCompat.getDrawable(context, drawable.ic_wishlist)
      )
    )
    navigationItems.add(
      NavigationEntity(
        title = context.getString(string.e_magazine),
        icon = ContextCompat.getDrawable(context, drawable.ic_e_magazine)
      )
    )
    return Single.just(navigationItems)
  }
}