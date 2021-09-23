package com.albertgf.randomusers.common.di

import android.app.Application
import androidx.room.Room
import com.albertgf.randomusers.common.db.UserDb
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.network.RandomConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val commonModelsModule = module {
    single { UserMapper() }
    single { RandomConfig.with("http://api.randomuser.me/", debug = true) }
    fun provideDB(application: Application): UserDb {
        return Room.databaseBuilder(
            application,
            UserDb::class.java,
            "user_db"
        ).build()
    }
    single { provideDB(androidApplication())}
}