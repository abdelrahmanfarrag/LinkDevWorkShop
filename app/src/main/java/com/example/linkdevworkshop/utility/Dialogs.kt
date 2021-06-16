package com.example.linkdevworkshop.utility

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.example.linkdevworkshop.R

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun Context.openLoadingDialog(): Dialog {
  val dialog = Dialog(this)
  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
  dialog.setContentView(R.layout.dialog_loading)
  dialog.window?.setLayout(
    WindowManager.LayoutParams.MATCH_PARENT,
    WindowManager.LayoutParams.MATCH_PARENT
  )
  return dialog
}