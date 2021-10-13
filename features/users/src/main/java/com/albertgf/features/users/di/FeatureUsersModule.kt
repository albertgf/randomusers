package com.albertgf.features.users.di

import com.albertgf.core.domain.UserRepository
import com.albertgf.core.usecases.DeleteUserUseCase
import com.albertgf.core.usecases.GetUserUseCase
import com.albertgf.core.usecases.GetUsersUseCase
import com.albertgf.features.users.detail.UserDetailViewModel
import com.albertgf.features.users.list.UsersListViewModel
import com.albertgf.features.users.models.UserUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureUsersModule = module {

    factory { UserRepository(get(named("DB")), get(named("API"))) }

    factory { GetUsersUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { DeleteUserUseCase(get()) }

    viewModel { UsersListViewModel(get(), get(), UserUiMapper()) }
    viewModel { UserDetailViewModel(get(), UserUiMapper()) }
}