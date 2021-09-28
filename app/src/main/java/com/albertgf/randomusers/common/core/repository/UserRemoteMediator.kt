package com.albertgf.randomusers.common.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.albertgf.randomusers.common.core.db.User
import com.albertgf.randomusers.common.core.db.UserDb
import com.albertgf.randomusers.common.core.network.RandomUserClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(private val database: UserDb, private val api: RandomUserClient) :
    RemoteMediator<Int, User>() {
    private val userDao = database.users()

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
                val response = api.users().body()?.results?.map {
                    User(
                        uid = it.login.uuid,
                        name = it.name.first,
                        surname = it.name.last,
                        email = it.email,
                        thumb = it.picture.thumb,
                        picture = it.picture.large,
                        phone = it.phone,
                        gender = it.gender,
                        street = it.location.street.name,
                        city = it.location.city,
                        state = it.location.state,
                        registeredDate = it.registered.date
                    )
                } ?: emptyList()

                if (response.isEmpty()) {
                    throw IOException()
                }

                database.runInTransaction {
                    userDao.insertAll(response)
                }
            }

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}