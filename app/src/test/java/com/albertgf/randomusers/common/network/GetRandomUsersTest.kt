package com.albertgf.randomusers.common.network

import org.junit.Before
import org.junit.Test

class GetRandomUsersTest : ApiClientTest() {

    private lateinit var client: RandomUserClient

    @Before
    fun setup() {
        client = RandomUserClient(config)
    }

    @Test
    fun get_random_users() {

        val response = client.users()

        assert(response.isSuccessful())
    }
}