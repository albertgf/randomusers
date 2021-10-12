package com.albertgf.features.users.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertgf.core.usecases.GetUserUseCase
import com.albertgf.features.users.models.UserUi
import com.albertgf.features.users.models.UserUiMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserDetailViewModel(private val getUser: GetUserUseCase, private val mapper: UserUiMapper) : ViewModel() {

    private val _user = MutableStateFlow(UserUi.blank())
    val user = _user.asStateFlow()

    fun loadUser(uid: String?) {
        viewModelScope.launch {
            val user = mapper.mapDomainToUi(getUser(uid ?: ""))
            _user.value = user
        }
    }
}