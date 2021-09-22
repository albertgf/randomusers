package com.albertgf.randomusers.common.network

import retrofit2.Call
import retrofit2.Response
import java.io.IOException

open class ApiClient constructor(private val apiConfig: RandomConfig) {

    fun <T> getApi(apiRest: Class<T>): T {
        return apiConfig.retrofit.create(apiRest)
    }

    protected fun <T>callApi(call: Call<T>) : ApiResponse<T> = try {
        val response: Response<T> = call.execute()

        when {
            response.code() >= 400 -> ApiResponse(response.code())
            response.code() == 200 -> ApiResponse(response.body()!!)
            else -> ApiResponse(ServerError)
        }
    } catch (e: IOException) {
        ApiResponse(NetworkError)
    }
}