package com.example.linkdevworkshop.presentation.ui.workshop.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.FragmentNewsDetailsBinding
import com.example.linkdevworkshop.di.presentation.fragment.FragmentSubComponent
import com.example.linkdevworkshop.presentation.ui.base.BaseActivity
import com.example.linkdevworkshop.presentation.ui.base.BaseFragment
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

  override fun setupInjection(fragmentSubComponent: FragmentSubComponent) {
    fragmentSubComponent.inject(this)
  }

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
    detailsArgs.article.apply {
      binding.newsDetailDescriptionTextView.text = description
      binding.newsDetailsTitleTextView.text = title
      binding.newsDetailsImageView.load(image)
      binding.newsDetailsDateTextView.text = publishedAt.convertDateToPattern()
      setOnOpenWebUrlClickListener(url)
    }

  }

  override fun releaseObjects() {
    _binding = null
  }

  private fun setOnOpenWebUrlClickListener(url: String) {
    binding.openWebSiteButton.setOnClickListener {
      (requireActivity() as BaseActivity).createChooserIntent(url)
    }
  }
}