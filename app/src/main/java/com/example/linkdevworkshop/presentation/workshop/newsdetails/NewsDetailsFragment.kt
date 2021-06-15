package com.example.linkdevworkshop.presentation.workshop.newsdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.FragmentNewsDetailsBinding
import com.example.linkdevworkshop.di.presentation.fragment.FragmentSubComponent
import com.example.linkdevworkshop.presentation.base.BaseActivity
import com.example.linkdevworkshop.presentation.base.BaseFragment
import com.example.linkdevworkshop.utility.extension.convertDateToPattern
import com.example.linkdevworkshop.utility.extension.createChooserIntent
import com.example.linkdevworkshop.utility.extension.getColor
import com.example.linkdevworkshop.utility.extension.load

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NewsDetailsFragment : BaseFragment() {

  private var _binding: FragmentNewsDetailsBinding? = null
  private val binding get() = _binding!!
  private val detailsArgs: NewsDetailsFragmentArgs by navArgs()


  override fun createViewBinding(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
    val view = binding.root
    view.setBackgroundColor(getColor(R.color.grey))
    return view
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    setHasOptionsMenu(true)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    menu.clear()
  }

  override fun onFragmentSetup(view: View, savedInstanceState: Bundle?) {
    detailsArgs.article.let { article ->
      binding.newsDetailDescriptionTextView.text = article.description
      binding.newsDetailsTitleTextView.text = article.title
      binding.newsDetailsImageView.load(article.image)
      binding.newsDetailsDateTextView.text = article.publishedAt.convertDateToPattern()
      setOnOpenWebUrlClickListener(article.url)
    }
  }

  override fun setupInjection(fragmentSubComponent: FragmentSubComponent) {
    fragmentSubComponent.inject(this)
  }

  override fun releaseObjects() {
    _binding = null
  }

  private fun setOnOpenWebUrlClickListener(url: String) {
    binding.openWebSiteButton.setOnClickListener {
      Log.d("printUrl",url)
      (requireActivity() as BaseActivity).createChooserIntent(url)
    }
  }
}