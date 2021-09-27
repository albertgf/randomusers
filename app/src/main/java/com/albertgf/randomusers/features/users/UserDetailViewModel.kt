package com.albertgf.randomusers.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.common.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserDetailViewModel(private val userRepository: UserRepository, private val userMapper: UserMapper) : ViewModel() {

    private val _user = MutableStateFlow(UserUi.blank())
    val user = _user.asStateFlow()

    fun loadUser(uid: String?) {
        viewModelScope.launch {
            val user = userMapper.mapDomainToUi(userRepository.getUser(uid ?: ""))
            _user.value = user
        }
    }
}