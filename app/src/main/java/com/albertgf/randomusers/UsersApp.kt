package com.albertgf.randomusers

import android.app.Application
import com.albertgf.features.users.di.featureUsersModule
import com.albertgf.randomusers.framework.di.apiModule
import com.albertgf.randomusers.framework.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@UsersApp)

            modules(listOf(
                apiModule,
                dbModule,
                featureUsersModule
            ))
        }
    }
}