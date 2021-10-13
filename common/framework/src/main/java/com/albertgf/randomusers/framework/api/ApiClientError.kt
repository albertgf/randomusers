package com.albertgf.randomusers.framework.api

import java.lang.Exception

sealed class ApiClientError : Exception()

object NetworkError : ApiClientError()
object ServerError : ApiClientError()
data class UnknownApiError( val code: Int, val errorMessage: String? = null ) : ApiClientError()