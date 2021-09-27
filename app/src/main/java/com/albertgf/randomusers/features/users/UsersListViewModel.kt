package com.albertgf.randomusers.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.common.models.presentation.UserUiMinimal
import com.albertgf.randomusers.common.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsersListViewModel(private val userRepository: UserRepository, private val userMapper: UserMapper) : ViewModel() {

    sealed class Event {
        data class NavigateToUser(val uid: String) : Event()
    }

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = _eventChannel.receiveAsFlow()

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    suspend fun getUsers() : Flow<PagingData<UserUiMinimal>> {
        val query = _filter.value

        return userRepository.getUsers(query).map { pagingData ->
            pagingData.map {
                userMapper.mapDomainToUiMinimal(it)
            }
        }
    }

    fun changeFilter(query: String) {
        _filter.value = query
    }

    fun deleteUser(uid: String) {
        viewModelScope.launch {
            userRepository.deleteUser(uid)
        }
    }

    fun openUser(uid: String) {
        viewModelScope.launch {
            _eventChannel.send(Event.NavigateToUser(uid))
        }
    }
}