package com.albertgf.core.domain

import androidx.paging.*
import com.albertgf.core.data.UserDataSource
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
class UserRemoteMediatorTest {

    private val mockDb = mockk<UserDataSource>()
    private val mockApi = mockk<UserDataSource>()

    @Test
    fun refresh_load_returns_success_but_more_data() = runBlocking {
        val remoteMediator = UserRemoteMediator(
            mockDb, mockApi
        )

        val pagingState = PagingState<Int, User>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        Assert.assertTrue( result is RemoteMediator.MediatorResult.Success )
        Assert.assertFalse( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun prepend_load_returns_success_but_more_data() = runBlocking {
        val remoteMediator = UserRemoteMediator(
            mockDb, mockApi
        )

        val pagingState = PagingState<Int, User>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )

        val result = remoteMediator.load(LoadType.PREPEND, pagingState)

        Assert.assertTrue( result is RemoteMediator.MediatorResult.Success )
        Assert.assertFalse( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }
}