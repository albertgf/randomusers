package com.albertgf.randomusers.common.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.albertgf.randomusers.common.core.db.User
import com.albertgf.randomusers.common.core.db.UserDb
import com.albertgf.randomusers.common.core.network.RandomUserClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(
    private val db: UserDb,
    private val api: RandomUserClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    @OptIn(ExperimentalPagingApi::class)
    suspend fun getUsers(query: String): Flow<PagingData<User>> {
        return withContext(dispatcher) {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false
                ),
                remoteMediator = UserRemoteMediator(db, api)
            ) {
                if (query.isEmpty()) {
                    db.users().getAll()
                } else {
                    db.users().findFiltered("%${query}%")
                }
            }.flow
        }
    }

    suspend fun deleteUser(uid: String) {
        return withContext(dispatcher) {
            db.users().deleteUser(uid)
        }
    }

    suspend fun getUser(uid: String) : User {
        return withContext(dispatcher) {
            db.users().getUser(uid)
        }
    }
}