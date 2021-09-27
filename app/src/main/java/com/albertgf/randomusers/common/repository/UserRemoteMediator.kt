package com.albertgf.randomusers.common.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.db.UserDb
import com.albertgf.randomusers.common.network.RandomUserClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(private val database: UserDb, private val api: RandomUserClient) : RemoteMediator<Int, User>() {
    private val userDao = database.users()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.uid
                }
            }

            withContext(Dispatchers.IO) {
                 val response = api.users().body()?.results?.map {
                    User(uid = it.login.uuid,
                        name = it.name.first,
                        surname = it.name.last,
                        email = it.email,
                        picture = it.picture.thumb,
                        phone = it.phone,
                        gender = it.gender,
                        street = it.location.street.name,
                        city = it.location.city,
                        state = it.location.state,
                        registeredDate = it.registered.date)
                } ?: emptyList()

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