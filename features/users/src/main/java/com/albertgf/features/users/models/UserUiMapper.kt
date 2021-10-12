package com.albertgf.features.users.models

import com.albertgf.common.coreview.extensions.formatTime
import com.albertgf.core.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserUiMapper(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
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