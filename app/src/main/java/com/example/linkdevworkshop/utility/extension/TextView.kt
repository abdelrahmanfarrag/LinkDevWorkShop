package com.example.linkdevworkshop.utility.extension

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */

fun TextView.spanDifferentTextSize(startCharIndex: Int, endCharIndex: Int) {
  val spannable = SpannableString(this.text)
  spannable.setSpan(
    RelativeSizeSpan(.7f),
    startCharIndex,
    endCharIndex,
    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
  )
  spannable.setSpan(
    ForegroundColorSpan(
      Color.BLACK
    ), endCharIndex + 1, this.text.length, 0
  )
  this.text = spannable
}
