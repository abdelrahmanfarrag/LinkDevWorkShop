package com.example.linkdevworkshop.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.linkdevworkshop.WorkShopApplication
import com.example.linkdevworkshop.di.presentation.activity.ActivitySubComponent

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
abstract class BaseActivity : AppCompatActivity() {

  /**
   * @SubComponentEntry
   */
  private val activitySubComponent: ActivitySubComponent by lazy {
    WorkShopApplication.get(this).appComponent.getActivitySubComponent().create(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    setupInjection(activitySubComponent)
    super.onCreate(savedInstanceState)
    setContentView(createViewBinding())
  }

  override fun onResume() {
    super.onResume()
    onActivityInitialized()
  }

  /**
   * Here is this function we will call any setup for views in Sub-Activity
   * Such as calling webservices ,observing web services response , and any additional actions on view we will but in this function
   * which will be overrode by any Sub-Activity
   */
  abstract fun onActivityInitialized()

  /**
   * This function will be overrode by any Child-Activity to do member field injection in each Activity
   */
  abstract fun setupInjection(activitySubComponent: ActivitySubComponent)

  abstract fun createViewBinding(): View

}
