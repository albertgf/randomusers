package com.albertgf.randomusers

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.albertgf.randomusers.databinding.MainActivityBinding
import com.albertgf.randomusers.features.users.UsersListViewModel
import com.albertgf.randomusers.features.users.adapter.UserListAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

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
    }

    private fun initList() {
        adapter = UserListAdapter()

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
}

