package com.example.linkdevworkshop.presentation.workshop

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.ActivityMainBinding
import com.example.linkdevworkshop.utility.extension.spanDifferentTextSize
import com.example.linkdevworkshop.utility.extension.toast

class MainActivity : AppCompatActivity() {

  private lateinit var navController: NavController
  private lateinit var binding: ActivityMainBinding
  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    setupNavigationDrawerWithActivity()
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
    binding.navigationHeaderView.headerTitleTextView.spanDifferentTextSize(0, 7)
  }
}