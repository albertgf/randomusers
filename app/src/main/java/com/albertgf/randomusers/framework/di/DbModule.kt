package com.albertgf.randomusers.framework.di

import android.app.Application
import androidx.room.Room
import com.albertgf.core.data.UserDataSource
import com.albertgf.randomusers.framework.db.MapperDb
import com.albertgf.randomusers.framework.db.RoomUserDataSource
import com.albertgf.randomusers.framework.db.UserDb
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dbModule = module {
    fun provideDB(application: Application): UserDb {
        return Room.databaseBuilder(
            application,
            UserDb::class.java,
            "user_db"
        ).build()
    }
    single { provideDB(androidApplication())}

    factory<UserDataSource>(named("DB")) { RoomUserDataSource(get(), MapperDb()) }
}