package com.albertgf.randomusers.common.network

import com.albertgf.randomusers.common.network.apimodels.ResultsApi

class RandomUserClient(apiConfig: RandomConfig) : ApiClient(apiConfig){

    fun users() : ApiResponse<ResultsApi> {
        return callApi(getApi(RandomUserRest::class.java).getUsers())
    }
}