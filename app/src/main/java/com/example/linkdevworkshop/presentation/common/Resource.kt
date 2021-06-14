package com.example.linkdevworkshop.presentation.common

import com.example.linkdevworkshop.utility.Error.GENERAL


class Resource<out T> constructor(
  val state: ResourceState,
  val data: T? = null,
  val message: String = GENERAL
)