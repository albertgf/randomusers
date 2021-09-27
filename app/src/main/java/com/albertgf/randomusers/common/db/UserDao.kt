package com.albertgf.randomusers.common.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE uid = :uid")
    fun getUser(uid: String) : User

    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAll(): PagingSource<Int, User>

    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAllTest(): List<User>

    @Query("SELECT COUNT(uid) FROM users WHERE deleted = 0")
    fun getCount() : Int

    @Query("SELECT * FROM users Where name Like :query OR surname LIKE :query OR email LIKE :query")
    fun findFiltered(query: String) : PagingSource<Int, User>

    @Query("SELECT * FROM users Where name Like :query OR surname LIKE :query OR email LIKE :query")
    fun findFilteredTest(query: String) : List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<User>)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    @Transaction
    fun deleteUser(uid: String) {
        val user = getUser(uid)
        user.deleted = true
        updateUser(user)
    }
}