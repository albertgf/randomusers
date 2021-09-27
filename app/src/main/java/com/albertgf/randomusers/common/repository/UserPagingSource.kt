package com.albertgf.randomusers.common.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.network.RandomUserClient
import com.albertgf.randomusers.common.network.apimodels.UserApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val api: RandomUserClient, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: 0

        return try {
            val response = withContext(dispatcher) { api.users()}

            val users = response.body()?.results?.map {
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

            val nextKey = if (users.isEmpty()) null else {
                pageIndex + (params.loadSize / 40)
            }

            LoadResult.Page(
                data = users,
                prevKey = if (pageIndex == 0) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}