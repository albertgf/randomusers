package com.albertgf.core.domain

import androidx.paging.*
import com.albertgf.core.data.UserDataSource
import kotlinx.coroutines.flow.Flow

class UserRepository(private val database: UserDataSource, private val api: UserDataSource) {

    suspend fun getUser(uid: String): User {
        return database.getById(uid)
    }

    @ExperimentalPagingApi
    suspend fun getUsers() : Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(database, api = api)
        ) {
            database.list()
        }.flow
    }

    @ExperimentalPagingApi
    suspend fun getUsersByQuery(query: String) : Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(database, api = api)
        ) {
            database.listByQuery(query)
        }.flow
    }

    suspend fun removeUser(uid: String) {
        database.delete(uid)
    }
}