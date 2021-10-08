package com.albertgf.randomusers

import android.app.Application
import com.albertgf.randomusers.common.core.di.commonModelsModule
import com.albertgf.randomusers.features.users.di.featureUsersModule
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
                commonModelsModule,
                featureUsersModule
            ))
        }
    }
}