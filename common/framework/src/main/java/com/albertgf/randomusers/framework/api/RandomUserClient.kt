package com.albertgf.randomusers.framework.api

import com.albertgf.randomusers.framework.api.apimodels.ResultsApi

class RandomUserClient(apiConfig: RandomConfig) : ApiClient(apiConfig){

    fun users() : ApiResponse<ResultsApi> {
        return callApi(getApi(RandomUserRest::class.java).getUsers())
    }
}