package com.albertgf.randomusers.common.core.network

import com.albertgf.randomusers.common.core.network.apimodels.ResultsApi
import retrofit2.Call
import retrofit2.http.GET

internal interface RandomUserRest {

    @GET("/?results=10")
    fun getUsers(): Call<ResultsApi>
}