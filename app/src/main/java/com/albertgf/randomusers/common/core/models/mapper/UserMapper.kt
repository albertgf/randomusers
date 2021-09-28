package com.albertgf.randomusers.common.core.models.mapper

import com.albertgf.randomusers.common.core.db.User
import com.albertgf.randomusers.common.coreview.extensions.formatTime
import com.albertgf.randomusers.common.core.models.presentation.UserUi
import com.albertgf.randomusers.common.core.models.presentation.UserUiMinimal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserMapper(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun mapDomainListToUi(
        domainUser: List<User>
    ): List<UserUiMinimal> {
        return withContext(dispatcher) {
            domainUser.map {
                mapDomainToUiMinimal(it)
            }
        }
    }

    suspend fun mapDomainToUi(domain: User) : UserUi =
        withContext(dispatcher) {
            UserUi(
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
                domain.registeredDate.formatTime()
            )
        }


    suspend fun mapDomainToUiMinimal(domain: User) : UserUiMinimal =
        withContext(dispatcher) {
         UserUiMinimal(
            domain.uid,
            domain.name,
            domain.surname,
            domain.email,
            domain.thumb,
            domain.phone
        )
    }
}