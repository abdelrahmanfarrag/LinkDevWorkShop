package com.example.linkdevworkshop.domain.usecase.navigation

import com.example.linkdevworkshop.domain.repository.navigation.NavigationRepository
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
class NavigationUseCase @Inject constructor(private val navigationRepository: NavigationRepository) {

  operator fun invoke() = navigationRepository.getNavigationList()
}