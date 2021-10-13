package com.albertgf.randomusers.framework.db

import androidx.paging.PagingSource
import androidx.room.*
import com.albertgf.core.domain.User
import com.albertgf.randomusers.framework.db.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE uid = :uid AND deleted = 0")
    fun getUser(uid: String) : UserEntity

    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAll(): PagingSource<Int, User>

    @Query("SELECT * FROM users WHERE deleted = 0")
    fun getAllTest(): List<UserEntity>

    @Query("SELECT COUNT(uid) FROM users WHERE deleted = 0")
    fun getCount() : Int

    @Query("SELECT * FROM users Where deleted = 0 AND (name Like :query OR surname LIKE :query OR email LIKE :query)")
    fun findFiltered(query: String) : PagingSource<Int, User>

    @Query("SELECT * FROM users Where deleted = 0 AND (name Like :query OR surname LIKE :query OR email LIKE :query)")
    fun findFilteredTest(query: String) : List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserEntity>)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Transaction
    fun deleteUser(uid: String) {
        val user = getUser(uid)
        user.deleted = true
        updateUser(user)
    }
}