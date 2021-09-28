package com.albertgf.randomusers

import android.app.Application
import com.albertgf.randomusers.common.core.di.commonModelsModule
import com.albertgf.randomusers.features.users.di.featureUsersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@UsersApp)

            modules(listOf(
                commonModelsModule,
                featureUsersModule
            ))
        }
    }
}