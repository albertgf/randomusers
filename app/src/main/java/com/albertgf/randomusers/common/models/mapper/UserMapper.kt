package com.albertgf.randomusers.common.models.mapper

import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.models.presentation.UserUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserMapper(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun mapDomainListToUi(
        domainUser: List<User>
    ): List<UserUi> {
        return withContext(dispatcher) {
            domainUser.map {
                mapDomainToUi(it)
            }
        }
    }

    fun mapDomainToUi(domain: User) : UserUi {
        return UserUi(
            domain.uid,
            domain.name,
            domain.surname,
            domain.email,
            domain.picture,
            domain.phone,
            domain.gender,
            domain.street,
            domain.city,
            domain.state,
            domain.registeredDate
        )
    }
}