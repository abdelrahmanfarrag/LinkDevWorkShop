package com.example.linkdevworkshop.di.presentation.fragment

import androidx.lifecycle.ViewModel
import com.example.linkdevworkshop.di.presentation.binding.ViewModelKey
import com.example.linkdevworkshop.di.presentation.scope.PerFragment
import com.example.linkdevworkshop.di.presentation.viewmodel.ViewModelProviderModule
import com.example.linkdevworkshop.presentation.workshop.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Module(includes = [ViewModelProviderModule::class])
abstract class FragmentViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(NewsViewModel::class)
  abstract fun bindPlayingMoviesViewModel(newsViewModel: NewsViewModel): ViewModel

}