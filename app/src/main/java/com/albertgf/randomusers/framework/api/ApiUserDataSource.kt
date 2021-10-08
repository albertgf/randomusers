package com.albertgf.randomusers.framework.api

import androidx.paging.PagingSource
import com.albertgf.core.data.UserDataSource
import com.albertgf.core.domain.User

class ApiUserDataSource(private val api: RandomUserClient, private val mapper: ApiMapper) : UserDataSource {
    override suspend fun add(user: User) {

    }

    override suspend fun addAll(users: List<User>) {

    }

    override suspend fun getById(uid: String): User {
        TODO ("never call")
    }

    override suspend fun get(): List<User> {
        val response = api.users()

        return if (response.isSuccessful()) {
            response.body()!!.results.map {
                mapper.mapToDomain(it)
            }
        } else {
            emptyList()
        }
    }

    override fun list(): PagingSource<Int, User> {
        TODO ("never call")
    }

    override fun listByQuery(query: String): PagingSource<Int, User> {
        TODO("never call")
    }

    override suspend fun delete(uid: String) {

    }
}