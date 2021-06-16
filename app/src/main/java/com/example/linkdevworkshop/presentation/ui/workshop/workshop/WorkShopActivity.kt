package com.example.linkdevworkshop.presentation.ui.workshop.workshop

import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.ActivityMainBinding
import com.example.linkdevworkshop.di.presentation.activity.ActivitySubComponent
import com.example.linkdevworkshop.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.linkdevworkshop.presentation.ui.base.BaseActivity
import com.example.linkdevworkshop.presentation.ui.workshop.workshop.adapter.NavigationMenuAdapter
import com.example.linkdevworkshop.utility.extension.getViewModel
import com.example.linkdevworkshop.utility.extension.spanDifferentTextSize
import com.example.linkdevworkshop.utility.extension.toast
import javax.inject.Inject

class WorkShopActivity : BaseActivity() {

  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var navigationMenuAdapter: NavigationMenuAdapter
  private lateinit var navController: NavController
  private lateinit var binding: ActivityMainBinding
  private lateinit var appBarConfiguration: AppBarConfiguration

  private val workShopViewModel by lazy {
    getViewModel(WorkShopViewModel::class.java, factoryProvider)
  }

  override fun onActivityInitialized() {
    setupNavigationDrawerWithActivity()
    setupNavigationMenu()
  }

  override fun setupInjection(activitySubComponent: ActivitySubComponent) {
    activitySubComponent.inject(this)
  }

  override fun createViewBinding(): View {
    binding = ActivityMainBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.options_menu, menu)
    val searchView = menu?.findItem(R.id.searchOptionItem)?.actionView as SearchView
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        toast(query ?: "")
        return true
      }

      override fun onQueryTextChange(query: String?): Boolean {
        return true
      }
    })
    return super.onCreateOptionsMenu(menu)
  }

  private fun setupNavigationDrawerWithActivity() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.hostFragmentContainerView) as NavHostFragment
    navController = navHostFragment.findNavController()
    setSupportActionBar(binding.toolbar)
    appBarConfiguration =
      AppBarConfiguration(
        setOf(R.id.newsFragment),
        binding.drawerLayout
      )
    setupActionBarWithNavController(navController, appBarConfiguration)
    binding.navDrawer.setupWithNavController(navController)
  }

  private fun setupNavigationMenu() {
    binding.navigationHeaderView.headerTitleTextView.spanDifferentTextSize(0, 7)
    workShopViewModel.getNavigationMenu()
    workShopViewModel.navigationList.observe(this, {
      setTheme(R.style.Theme_LinkDevWorkShop)
      navigationMenuAdapter.setOnItemClicked { navigationMenu ->
        toast(navigationMenu.title)
        workShopViewModel.updateSelectedNavigationMenu(navigationMenu)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
      }
      binding.navigationItemRV.adapter = navigationMenuAdapter
      navigationMenuAdapter.setItems(it)
    })
  }
}