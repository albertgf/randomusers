package com.albertgf.randomusers.common.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAll(): PagingSource<Int, User>

    @Query("SELECT COUNT(uid) FROM users WHERE deleted = 0")
    fun getCount() : Int

    @Query("SELECT * FROM users Where name Like :name")
    fun findByName(name: String) : List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<User>)

    @Update
    fun deleteUserFromQuery(user: User)

    @Delete
    fun delete(user: User)
}