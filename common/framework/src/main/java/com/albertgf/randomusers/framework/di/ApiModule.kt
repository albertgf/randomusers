package com.albertgf.randomusers.framework.di

import com.albertgf.core.data.UserDataSource
import com.albertgf.randomusers.framework.api.ApiMapper
import com.albertgf.randomusers.framework.api.ApiUserDataSource
import com.albertgf.randomusers.framework.api.RandomConfig
import com.albertgf.randomusers.framework.api.RandomUserClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiModule = module {

    factory<UserDataSource>(named("API")) { ApiUserDataSource(RandomUserClient(get()), ApiMapper()) }

    single { RandomConfig.with("http://api.randomuser.me/", debug = true) }
}