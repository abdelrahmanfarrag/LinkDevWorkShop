package com.example.linkdevworkshop.data.remote.validator

import com.example.linkdevworkshop.data.remote.response.ErrorResponse
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
class Validator @Inject constructor(private val gson: Gson) {

  fun <T> validateApiResponse(response: Response<T>?): Resource<T> {
    response?.let {
      it.apply {
        if (this.code() == 429)
          handErrorResponse(response)
        else {
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
    }
    return Resource(ERROR, message = GENERAL)
  }

  private fun <T> handErrorResponse(response: Response<T>): Resource<T> {
    val errorResponse = gson.fromJson(response.errorBody()?.toString(), ErrorResponse::class.java)
    return Resource(ERROR, message = errorResponse.message)
  }

  private fun modelToJson(obj: Any): String {
    return gson.toJson(obj)
  }
}