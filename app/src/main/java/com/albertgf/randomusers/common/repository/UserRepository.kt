package com.albertgf.randomusers.common.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.db.UserDb
import com.albertgf.randomusers.common.network.RandomUserClient
import kotlinx.coroutines.flow.Flow

class UserRepository(private val db: UserDb, private val api: RandomUserClient) {

    @OptIn(ExperimentalPagingApi::class)
    fun getUsers(): Flow<PagingData<User>> = Pager(
            config = PagingConfig(
                pageSize = 40,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(db, api)
        ) {
        db.users().getAll()
    }.flow

    fun getUsersFiltered(name: String, surname: String, email: String): Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false),
            pagingSourceFactory = { db.users().findFiltered(name, surname, email)}
        ).flow

}