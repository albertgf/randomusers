package com.albertgf.randomusers.common.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAll(): PagingSource<Int, User>

    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAllTest(): List<User>

    @Query("SELECT COUNT(uid) FROM users WHERE deleted = 0")
    fun getCount() : Int

    @Query("SELECT * FROM users Where name Like :name OR surname LIKE :surname OR email LIKE :email")
    fun findFiltered(name: String, surname: String, email: String) : PagingSource<Int, User>

    @Query("SELECT * FROM users Where name Like :name OR surname LIKE :surname OR email LIKE :email")
    fun findFilteredTest(name: String, surname: String, email: String) : List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<User>)

    @Update
    fun deleteUserFromQuery(user: User)

    @Delete
    fun delete(user: User)
}