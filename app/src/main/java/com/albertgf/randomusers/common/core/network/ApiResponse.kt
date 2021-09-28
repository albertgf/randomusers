package com.albertgf.randomusers.common.core.network

import java.lang.Exception

class ApiResponse<T>() {

    private var t: T? = null
    private var code: Int = -1
    private var exception : Exception? = null

    constructor (t: T, code: Int = 200) : this() {
        this.t = t
        this.code = code
    }

    constructor (code: Int, message: String? = null) : this() {
        this.code = code
        exception = inspectResponseForApiErrors(code, message)
    }

    constructor(exception: Exception) : this () {
        this.exception = exception
    }

    fun isSuccessful() : Boolean {
        return (t != null && exception == null) || code < 300
    }

    fun body() : T? {
        return t
    }

    private fun inspectResponseForApiErrors(code: Int, message: String? = null) : ApiClientError = when(code) {
        500 -> ServerError
        else -> UnknownApiError(code, message)
    }
}