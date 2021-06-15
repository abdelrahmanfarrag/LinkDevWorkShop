package com.example.linkdevworkshop.di.presentation.activity

import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.di.presentation.binding.ViewModelKey
import com.example.linkdevworkshop.di.presentation.viewmodel.ViewModelProviderModule
import com.example.linkdevworkshop.presentation.workshop.workshop.WorkShopViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 15 Jun, 2021.
 */
@Module(includes = [ViewModelProviderModule::class])
abstract class ActivityViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(WorkShopViewModel::class)
  abstract fun bindsWorkShopViewModel(workShopViewModel: WorkShopViewModel): ViewModel
}