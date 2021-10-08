package com.albertgf.core.usecases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.albertgf.core.domain.User
import com.albertgf.core.domain.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val repository: UserRepository) {

    @ExperimentalPagingApi
    suspend operator fun invoke(query: String): Flow<PagingData<User>> {
        return if (query.isNotEmpty())
            repository.getUsersByQuery(query) else repository.getUsers()
    }
}