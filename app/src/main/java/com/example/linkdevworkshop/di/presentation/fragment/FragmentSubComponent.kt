package com.example.linkdevworkshop.di.presentation.fragment

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Subcomponent(modules = [FragmentViewModelModule::class, FragmentModule::class])
interface FragmentSubComponent {


  @Subcomponent.Factory
  interface Factory {
    fun create(@BindsInstance fragment: Fragment): FragmentSubComponent
  }
}