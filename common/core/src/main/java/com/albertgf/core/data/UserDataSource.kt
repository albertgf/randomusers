package com.albertgf.core.data

import androidx.paging.PagingSource
import com.albertgf.core.domain.User

interface UserDataSource {
    suspend fun add(user: User)

    suspend fun addAll(users: List<User>)

    suspend fun getById(uid: String) : User

    suspend fun get() : List<User>

    fun list(): PagingSource<Int, User>

    fun listByQuery(query: String): PagingSource<Int, User>

    suspend fun delete(uid: String)
}