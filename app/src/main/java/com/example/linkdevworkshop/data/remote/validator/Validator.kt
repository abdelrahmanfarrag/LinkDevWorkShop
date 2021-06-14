package com.example.linkdevworkshop.data.remote.validator

import com.example.linkdevworkshop.presentation.common.Resource
import com.example.linkdevworkshop.presentation.common.ResourceState.ERROR
import com.example.linkdevworkshop.presentation.common.ResourceState.SUCCESS
import com.example.linkdevworkshop.utility.Error.GENERAL
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class Validator @Inject constructor(private val jsonResponse: Gson) {

  fun <T> validateApiResponse(response: Response<T>?): Resource<T> {
    response?.let {
      it.apply {
        if (isSuccessful) {
          val model = response.body()
          if (model != null) {
            modelToJson(model)
            return Resource(SUCCESS, data = model)
          }
        } else {
          return Resource(ERROR, message = GENERAL)

        }
      }
    }
    return Resource(ERROR, message = GENERAL)
  }

  private fun modelToJson(obj: Any): String {
    return jsonResponse.toJson(obj)
  }
}