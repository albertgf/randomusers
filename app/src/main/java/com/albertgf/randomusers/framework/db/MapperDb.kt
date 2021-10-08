package com.albertgf.randomusers.framework.db

import com.albertgf.core.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MapperDb(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun mapToDb(domain: User) : UserEntity =
        withContext(dispatcher) {
            UserEntity(
                uid = domain.uid,
                name = domain.name,
                surname = domain.surname,
                email = domain.email,
                thumb = domain.thumb,
                picture = domain.picture,
                phone = domain.phone,
                gender = domain.gender,
                street = domain.street,
                city = domain.city,
                state = domain.state,
                registeredDate = domain.registeredDate
            )
        }

    suspend fun mapToDomain(entity: UserEntity) : User =
        withContext(dispatcher) {
            User(
                uid = entity.uid,
                name = entity.name,
                surname = entity.surname,
                email = entity.email,
                thumb = entity.thumb,
                picture = entity.picture,
                phone = entity.phone,
                gender = entity.gender,
                street = entity.street,
                city = entity.city,
                state = entity.state,
                registeredDate = entity.registeredDate
            )
        }

}