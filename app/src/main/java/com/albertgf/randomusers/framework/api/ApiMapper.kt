package com.albertgf.randomusers.framework.api

import com.albertgf.core.domain.User
import com.albertgf.randomusers.common.core.models.presentation.UserUi
import com.albertgf.randomusers.common.coreview.extensions.formatTime
import com.albertgf.randomusers.framework.api.apimodels.UserApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiMapper(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun mapToDomain(api: UserApi) : User =
        withContext(dispatcher) {
            User(
                uid = api.login.uuid,
                name = api.name.first,
                surname = api.name.last,
                email = api.email,
                thumb = api.picture.thumb,
                picture = api.picture.large,
                phone = api.phone,
                gender = api.gender,
                street = api.location.street.name,
                city = api.location.city,
                state = api.location.state,
                registeredDate = api.registered.date
            )
        }
}