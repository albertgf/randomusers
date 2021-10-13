package com.albertgf.randomusers.framework.db

import androidx.paging.*
import com.albertgf.core.data.UserDataSource
import com.albertgf.core.domain.User

class RoomUserDataSource(private val db: UserDb, private val mapper: MapperDb) : UserDataSource {
    override suspend fun add(user: User) {

    }

    override suspend fun addAll(users: List<User>) {
        db.users().insertAll(users.map { user ->
            mapper.mapToDb(user)
        })
    }

    override suspend fun getById(uid: String): User {
        return mapper.mapToDomain(db.users().getUser(uid))
    }

    override suspend fun get(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(uid: String) {
        db.users().deleteUser(uid)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun list(): PagingSource<Int, User> {
        return db.users().getAll()
    }

    override fun listByQuery(query: String): PagingSource<Int, User> {
        return db.users().findFiltered(query = "%${query}%")
    }
}