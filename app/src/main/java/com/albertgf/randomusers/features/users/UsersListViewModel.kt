package com.albertgf.randomusers.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.common.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersListViewModel(private val userRepository: UserRepository, private val userMapper: UserMapper) : ViewModel() {

    fun getUsers(): Flow<PagingData<UserUi>> {
        return userRepository.getUsers().map { pagingData ->
            pagingData.map {
                userMapper.mapDomainToUi(it)
            }
        }.cachedIn(viewModelScope)
    }
}