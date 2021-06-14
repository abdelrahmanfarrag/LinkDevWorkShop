package com.example.linkdevworkshop.utility.extension

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.linkdevworkshop.presentation.base.BaseFragment
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState
import com.example.linkdevworkshop.utility.openLoadingDialog

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */

/**
 * in any fragment when you need a instantiate a @ViewModel call this extension function
 */
fun <T : ViewModel> BaseFragment.getViewModel(
  viewModelClass: Class<T>,
  factory: ViewModelProvider.Factory? = null
): T {
  return factory?.let { viewModelFactory ->
    ViewModelProvider(this, viewModelFactory).get(viewModelClass)
  } ?: ViewModelProvider(this).get(viewModelClass)
}

/**
 * if any fragment shares @ViewModel with any other fragment we @Must use this extension function
 */
fun <T : ViewModel> BaseFragment.getSharedViewModel(
  viewModelClass: Class<T>,
  factory: ViewModelProvider.Factory? = null
): T {
  return factory?.let { viewModelFactory ->
    ViewModelProvider(requireActivity(), viewModelFactory).get(viewModelClass)
  } ?: ViewModelProvider(requireActivity()).get(viewModelClass)
}

fun BaseFragment.getColor(@ColorRes res: Int) =
  requireActivity().apply { ContextCompat.getColor(requireContext(), res) }

fun BaseFragment.getDrawable(@DrawableRes res: Int) =
  requireActivity().apply { ContextCompat.getDrawable(requireContext(), res) }

fun BaseFragment.hideKeypad() {
  activity?.let { activity ->
    requireActivity().currentFocus?.let { view ->
      val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}

/**
 *
 * @Create a toast message
 */
fun BaseFragment.toast(msg: String) {
  Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}


fun BaseFragment.adjustViewPan() {
  requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
}


/**
 * This extension function helps to get the data from the @ViewModel to @Operate on it
 * @FirstParam is to get the data from your @ViewModel
 * @SecondParam is to update the @View with data iff the operation were success.
 * @ThirdParam is to send the error to view to do desired action based on error sent.
 * @Loading To show loading dialog
 * @Error to hide the loading dialog and show specified error .
 *
 */
fun <T, L : LiveData<Resource<T>>> BaseFragment.observingLiveDataOfFragment(
  result: L,
  successAction: (T?) -> Unit,
  failureAction: (String) -> Unit

) {
  val dialog = this.requireContext().openLoadingDialog()
  result.observe(viewLifecycleOwner, Observer { resourceOfResponse ->
    when (resourceOfResponse.state) {
      ResourceState.LOADING -> dialog.show()
      ResourceState.SUCCESS -> {
        successAction(resourceOfResponse.data)
        dialog.dismiss()
      }
      ResourceState.ERROR -> {
        failureAction(resourceOfResponse.message)
        dialog.dismiss()
      }
    }
  })
}