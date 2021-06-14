package com.example.linkdevworkshop.utility

/**
 * Authored by Abdelrahman Ahmed on 14 Jun, 2021.
 */
object Error {
  const val GENERAL = "_error"
  const val NETWORK = "_network"
  const val MAX_REQUESTS_COUNT_REACHED = "max_request_error"
  object Code{
    const val MAX_REQUESTS_COUNT_REACHED = "rateLimited"

  }
}