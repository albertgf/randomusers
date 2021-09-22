package com.albertgf.randomusers.common.network

open class ApiClientTest {
    companion object {
        private const val baseUrl = "http://api.randomuser.me/"
        val config = RandomConfig.with(baseUrl, true)
    }
}