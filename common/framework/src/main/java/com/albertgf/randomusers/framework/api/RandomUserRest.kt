package com.albertgf.randomusers.framework.api

import com.albertgf.randomusers.framework.api.apimodels.ResultsApi
import retrofit2.Call
import retrofit2.http.GET

internal interface RandomUserRest {

    @GET("/?results=10")
    fun getUsers(): Call<ResultsApi>
}