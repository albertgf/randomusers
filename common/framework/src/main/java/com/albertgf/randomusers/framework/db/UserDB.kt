package com.albertgf.randomusers.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDb : RoomDatabase() {
    companion object {
        fun create(context: Context): UserDb {
            return Room.databaseBuilder(context, UserDb::class.java, "user_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun users(): UserDao
}