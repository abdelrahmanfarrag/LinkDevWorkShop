package com.example.linkdevworkshop.utility.extension

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.linkdevworkshop.presentation.ui.base.BaseActivity
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.LOADING
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS
import com.example.linkdevworkshop.utility.openLoadingDialog


/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun BaseActivity.toast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T : ViewModel> BaseActivity.getViewModel(
  modelClass: Class<T>,
  viewModelFactory: ViewModelProvider.Factory? = null
): T {
  return viewModelFactory?.let { factory ->
    ViewModelProvider(this as ViewModelStoreOwner, factory).get(modelClass)
  } ?: ViewModelProvider(this as ViewModelStoreOwner).get(modelClass)
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
fun <T, L : LiveData<Resource<T>>> BaseActivity.observingResourceOfLiveDataOfActivity(
  result: L,
  successAction: (T?) -> Unit,
  failureAction: ((String) -> Unit)? = null

) {
  val dialog = this.openLoadingDialog()
  result.observe(this, { resourceOfResponse ->
    when (resourceOfResponse.state) {
      LOADING -> dialog.show()
      SUCCESS -> {
        successAction(resourceOfResponse.data)
        dialog.dismiss()
      }
      ERROR -> {
        if (failureAction != null) {
          failureAction(resourceOfResponse.message)
          dialog.dismiss()
        }
      }
    }
  })
}

fun BaseActivity.createChooserIntent(url: String) {
  val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
  startActivity(browserIntent)
}
