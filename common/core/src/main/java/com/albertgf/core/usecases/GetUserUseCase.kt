package com.albertgf.core.usecases

import com.albertgf.core.domain.User
import com.albertgf.core.domain.UserRepository

class GetUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(uid: String) : User {
        return repository.getUser(uid)
    }
}