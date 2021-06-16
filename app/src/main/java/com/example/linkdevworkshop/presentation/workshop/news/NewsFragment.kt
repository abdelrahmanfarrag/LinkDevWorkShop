package com.example.linkdevworkshop.presentation.workshop.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.FragmentNewsBinding
import com.example.linkdevworkshop.di.presentation.fragment.FragmentSubComponent
import com.example.linkdevworkshop.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.linkdevworkshop.presentation.base.BaseFragment
import com.example.linkdevworkshop.presentation.workshop.news.adapter.NewsAdapter
import com.example.linkdevworkshop.utility.Error
import com.example.linkdevworkshop.utility.extension.getColor
import com.example.linkdevworkshop.utility.extension.getViewModel
import com.example.linkdevworkshop.utility.extension.gone
import com.example.linkdevworkshop.utility.extension.observingLiveDataOfFragment
import com.example.linkdevworkshop.utility.extension.visible
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NewsFragment : BaseFragment() {

  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var newsAdapter: NewsAdapter
  private var _binding: FragmentNewsBinding? = null
  private val binding get() = _binding!!
  private val newsViewModel by lazy {
    getViewModel(NewsViewModel::class.java, factoryProvider)
  }

  override fun createViewBinding(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNewsBinding.inflate(inflater, container, false)
    val view = binding.root
    view.setBackgroundColor(getColor(R.color.grey))
    return view
  }

  override fun onFragmentSetup(view: View, savedInstanceState: Bundle?) {
    newsViewModel.getCombinedNewsArticle()
    observingLiveDataOfFragment(newsViewModel.articles, { articles ->
      updateUiWhenErrorHappens(false)
      newsAdapter.setOnArticleClicked { article ->
        val direction = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(article)
        findNavController().navigate(direction)
      }
      _binding?.newsRecyclerView?.adapter = newsAdapter
      newsAdapter.setItems(articles?.articlesUi ?: mutableListOf())
    }, {
      when (it) {
        Error.GENERAL -> updateUiWhenErrorHappens(true, getString(R.string.error_happened))
        Error.NETWORK -> updateUiWhenErrorHappens(true, getString(R.string.network_error))
        else -> updateUiWhenErrorHappens(true, it)
      }
    })
  }

  override fun setupInjection(fragmentSubComponent: FragmentSubComponent) {
    fragmentSubComponent.inject(this)
  }

  override fun releaseObjects() {
    _binding = null
  }

  private fun updateUiWhenErrorHappens(isError: Boolean, message: String = "") {
    if (isError) {
      _binding?.newsRecyclerView?.gone()
      _binding?.errorIndicatorTextView?.visible()
      _binding?.errorIndicatorTextView?.text = message
    } else {
      _binding?.newsRecyclerView?.visible()
      _binding?.errorIndicatorTextView?.gone()
      _binding?.errorIndicatorTextView?.text = message
    }
  }
}