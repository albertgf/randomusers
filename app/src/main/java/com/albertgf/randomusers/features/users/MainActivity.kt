package com.albertgf.randomusers.features.users

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.lifecycleScope
import com.albertgf.randomusers.R
import com.albertgf.randomusers.common.extensions.collectFlow
import com.albertgf.randomusers.databinding.MainActivityBinding
import com.albertgf.randomusers.features.users.adapter.UserListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding get() = _binding!!

    private val usersViewModel: UsersListViewModel by viewModel()

    private var adapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        initSearchBox()
        collectUiState()

        this.collectFlow(::setupEventsViewModel)
    }

    private fun initList() {
        adapter = UserListAdapter(usersViewModel)

        binding.rvUsers.adapter = adapter
    }

    private fun initSearchBox() {
        binding.searchBox.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val filter: String by usersViewModel.filter.collectAsState()
                MaterialTheme {


                    TextField(
                        value = filter,
                        onValueChange = { textValue ->
                            usersViewModel.changeFilter(textValue)
                            collectUiState()
                        },
                        label = { Text(stringResource(id = R.string.hint_search)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    private fun collectUiState() {
        lifecycleScope.launchWhenStarted {
            usersViewModel.getUsers().collectLatest { users ->
                adapter?.submitData(users)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        _binding = null
    }

    private suspend fun setupEventsViewModel() {
        usersViewModel.eventsFlow.collect {
            when (it) {
                is UsersListViewModel.Event.NavigateToUser -> {
                    navigateToUser(it.uid)
                }
            }
        }
    }

    private fun navigateToUser(uid: String) {
        val intent = Intent(baseContext, DetailActivity::class.java).apply {
            putExtra("uid", uid)
        }
        startActivity(intent)
    }
}

