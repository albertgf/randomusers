package com.albertgf.randomusers.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.common.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsersListViewModel(private val userRepository: UserRepository, private val userMapper: UserMapper) : ViewModel() {

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    suspend fun getUsers() : Flow<PagingData<UserUi>> {
        val query = _filter.value

        return userRepository.getUsers(query).map { pagingData ->
            pagingData.map {
                userMapper.mapDomainToUi(it)
            }
        }
    }

    fun changeFilter(query: String) {
        _filter.value = query
    }
}