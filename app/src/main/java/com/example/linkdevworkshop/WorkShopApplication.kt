package com.example.linkdevworkshop

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.linkdevworkshop.di.application.component.AppComponent
import com.example.linkdevworkshop.di.application.component.DaggerAppComponent

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class WorkShopApplication : Application() {

  lateinit var appComponent: AppComponent

  companion object {
    fun get(activity: FragmentActivity): WorkShopApplication {
      return activity.application as WorkShopApplication
    }
  }

  override fun onCreate() {
    super.onCreate()
    setupInjection()
  }

  private fun setupInjection() {
    appComponent = DaggerAppComponent.factory().create(this)
  }
}