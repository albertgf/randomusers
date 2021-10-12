package com.albertgf.core.usecases

import com.albertgf.core.domain.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteUserUseCase (private val repository: UserRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend operator fun invoke(uid: String) {
        withContext(dispatcher) {
            repository.removeUser(uid)
        }
    }
}