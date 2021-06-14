package com.example.linkdevworkshop.di.presentation.fragment

import androidx.fragment.app.Fragment
import com.example.linkdevworkshop.di.presentation.scope.PerFragment
import com.example.linkdevworkshop.presentation.workshop.news.NewsFragment
import com.example.linkdevworkshop.presentation.workshop.newsdetails.NewsDetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@PerFragment
@Subcomponent(modules = [FragmentViewModelModule::class, FragmentModule::class])
interface FragmentSubComponent {

  fun inject(newsFragment: NewsFragment)

  fun inject(newsDetailsFragment: NewsDetailsFragment)

  @Subcomponent.Factory
  interface Factory {
    fun create(@BindsInstance fragment: Fragment): FragmentSubComponent

  }
}