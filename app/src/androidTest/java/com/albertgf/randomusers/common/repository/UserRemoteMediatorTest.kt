package com.albertgf.randomusers.common.repository

import android.content.Context
import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.albertgf.randomusers.TestUtil
import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.db.UserDb
import com.albertgf.randomusers.common.network.RandomConfig
import com.albertgf.randomusers.common.network.RandomUserClient
import org.junit.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class UserRemoteMediatorTest {
    private val mockUsers = TestUtil.createUserList(10, different = true)
    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext<Context>(),
        UserDb::class.java
    ).build()
    private val api = RandomUserClient(apiConfig = RandomConfig.with("http://api.randomuser.me/", debug = true))

    @Test
    fun refreshLoadReturnsSuccessButMoreData() = runBlocking {
        val remoteMediator = UserRemoteMediator(
            mockDb, api
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
    fun prependLoadReturnsSuccessButMoreData() = runBlocking {
        val remoteMediator = UserRemoteMediator(
            mockDb, api
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

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }
}