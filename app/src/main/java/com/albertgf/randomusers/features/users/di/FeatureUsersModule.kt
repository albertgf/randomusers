package com.albertgf.randomusers.features.users.di

import com.albertgf.randomusers.common.network.RandomUserClient
import com.albertgf.randomusers.common.repository.UserRepository
import com.albertgf.randomusers.features.users.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureUsersModule = module {

    factory { UserRepository(get(), RandomUserClient(get())) }

    viewModel { UsersListViewModel(get(), get()) }
}