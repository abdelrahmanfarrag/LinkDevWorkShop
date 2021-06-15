package com.example.linkdevworkshop.di.presentation.activity

import android.app.Activity
import com.example.linkdevworkshop.di.presentation.scope.PerActivity
import com.example.linkdevworkshop.presentation.workshop.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class, ActivityViewModelModule::class])
interface ActivitySubComponent {

  fun inject(mainActivity: MainActivity)

  @Subcomponent.Factory
  interface Factory {
    fun create(@BindsInstance activity: Activity): ActivitySubComponent
  }
}