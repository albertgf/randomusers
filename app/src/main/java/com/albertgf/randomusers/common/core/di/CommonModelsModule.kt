package com.albertgf.randomusers.common.core.di

import com.albertgf.randomusers.common.core.models.mapper.UserMapper
import org.koin.dsl.module

val commonModelsModule = module {
    single { UserMapper() }

}