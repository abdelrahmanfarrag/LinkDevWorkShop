package com.example.linkdevworkshop.presentation.workshop.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkdevworkshop.R
import com.example.linkdevworkshop.databinding.FragmentNewsBinding
import com.example.linkdevworkshop.databinding.FragmentNewsDetailsBinding
import com.example.linkdevworkshop.di.presentation.fragment.FragmentSubComponent
import com.example.linkdevworkshop.presentation.base.BaseFragment
import com.example.linkdevworkshop.utility.extension.getColor

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NewsDetailsFragment : BaseFragment() {

  private var _binding: FragmentNewsDetailsBinding? = null
  private val binding get() = _binding!!

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

  override fun onFragmentSetup(view: View, savedInstanceState: Bundle?) {}

  override fun setupInjection(fragmentSubComponent: FragmentSubComponent) {
    fragmentSubComponent.inject(this)
  }

  override fun releaseObjects() {
    _binding = null
  }

}