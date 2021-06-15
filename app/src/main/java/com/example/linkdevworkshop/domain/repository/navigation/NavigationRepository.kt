package com.example.linkdevworkshop.domain.repository.navigation

import com.example.linkdevworkshop.domain.entity.navigation.NavigationEntity
import io.reactivex.Single

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
interface NavigationRepository {

  fun getNavigationList(): Single<List<NavigationEntity>>
}