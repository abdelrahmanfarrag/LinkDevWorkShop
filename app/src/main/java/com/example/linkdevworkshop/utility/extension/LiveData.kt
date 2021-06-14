package com.example.linkdevworkshop.utility.extension

import androidx.lifecycle.MutableLiveData
import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.LOADING
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(
  data: T
) = postValue(Resource(SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading(
) = postValue(Resource(LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(
  message: String
) = postValue(Resource(ERROR, value?.data, message))
