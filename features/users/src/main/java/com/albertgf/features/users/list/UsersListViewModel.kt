package com.albertgf.features.users.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import com.albertgf.core.usecases.DeleteUserUseCase
import com.albertgf.core.usecases.GetUsersUseCase
import com.albertgf.features.users.models.UserUiMapper
import com.albertgf.features.users.models.UserUiMinimal
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUseCase: DeleteUserUseCase,
    private val userMapper: UserUiMapper
) : ViewModel() {

    sealed class Event {
        data class NavigateToUser(val uid: String) : Event()
    }

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = _eventChannel.receiveAsFlow()

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    @OptIn(ExperimentalPagingApi::class)
    suspend fun getUsers(): Flow<PagingData<UserUiMinimal>> {
        val query = _filter.value

        return getUsersUseCase(query).map { pagingData ->
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
            deleteUseCase(uid)
        }
    }

    fun openUser(uid: String) {
        viewModelScope.launch {
            _eventChannel.send(Event.NavigateToUser(uid))
        }
    }
}