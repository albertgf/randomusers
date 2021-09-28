package com.albertgf.randomusers.features.users.di

import com.albertgf.randomusers.common.core.network.RandomUserClient
import com.albertgf.randomusers.common.core.repository.UserRepository
import com.albertgf.randomusers.features.users.detail.UserDetailViewModel
import com.albertgf.randomusers.features.users.list.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureUsersModule = module {

    factory { UserRepository(get(), RandomUserClient(get())) }

    viewModel { UsersListViewModel(get(), get()) }
    viewModel { UserDetailViewModel(get(), get()) }
}