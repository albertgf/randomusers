package com.albertgf.randomusers

import android.os.Bundle
import androidx.activity.ComponentActivity
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

        initView()
        collectUiState()
    }

    private fun initView() {
        adapter = UserListAdapter()

        binding.rvUsers.adapter = adapter
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

