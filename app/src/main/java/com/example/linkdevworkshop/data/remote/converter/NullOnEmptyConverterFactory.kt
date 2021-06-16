package com.example.linkdevworkshop.data.remote.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
class NullOnEmptyConverterFactory @Inject constructor() : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<Annotation>,
    retrofit: Retrofit
  ): Converter<ResponseBody, *> {
    val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
    @Suppress("RemoveExplicitTypeArguments")
    return Converter<ResponseBody, Any> { body ->
      if (body.contentLength() == 0L) null
      else delegate.convert(body)
    }
  }
}