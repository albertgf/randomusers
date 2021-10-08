package com.albertgf.randomusers.common.core.network

import com.albertgf.randomusers.framework.api.RandomConfig

open class ApiClientTest {
    companion object {
        private const val baseUrl = "http://api.randomuser.me/"
        val config = RandomConfig.with(baseUrl, true)
    }
}