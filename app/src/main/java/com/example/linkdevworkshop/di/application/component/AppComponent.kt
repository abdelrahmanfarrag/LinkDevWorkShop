package com.example.linkdevworkshop.di.application.component

import android.app.Application
import com.example.linkdevworkshop.di.application.module.network.ApiModule
import com.example.linkdevworkshop.di.application.scope.ApplicationScope
import com.example.linkdevworkshop.di.presentation.activity.ActivitySubComponent
import com.example.linkdevworkshop.di.presentation.fragment.FragmentSubComponent
import dagger.BindsInstance
import dagger.Component

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@ApplicationScope
@Component(modules = [ApiModule::class])
interface AppComponent {

  fun getActivitySubComponent(): ActivitySubComponent.Factory

  fun getFragmentSubComponent(): FragmentSubComponent.Factory

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance application: Application): AppComponent
  }
}