package com.albertgf.randomusers.common.core.network

import com.albertgf.randomusers.common.core.network.apimodels.ResultsApi

class RandomUserClient(apiConfig: RandomConfig) : ApiClient(apiConfig){

    fun users() : ApiResponse<ResultsApi> {
        return callApi(getApi(RandomUserRest::class.java).getUsers())
    }
}