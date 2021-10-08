package com.albertgf.core.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.albertgf.core.data.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(private val database: UserDataSource, private val api: UserDataSource) :
    RemoteMediator<Int, User>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                LoadType.APPEND -> {
                    val page = state.pages.size
                    if (page > 1)
                        state.lastItemOrNull()
                            ?: return MediatorResult.Success(endOfPaginationReached = false)
                }
            }

            withContext(Dispatchers.IO) {
                val response = api.get()

                if (response.isEmpty()) {
                    throw IOException()
                }

                database.addAll(response)
            }

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}