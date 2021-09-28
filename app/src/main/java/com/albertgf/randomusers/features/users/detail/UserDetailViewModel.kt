package com.albertgf.randomusers.features.users.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertgf.randomusers.common.core.models.mapper.UserMapper
import com.albertgf.randomusers.common.core.models.presentation.UserUi
import com.albertgf.randomusers.common.core.repository.UserRepository
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