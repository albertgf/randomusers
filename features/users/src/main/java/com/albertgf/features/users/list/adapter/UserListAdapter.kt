package com.albertgf.features.users.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.albertgf.features.users.databinding.ItemUserBinding
import com.albertgf.features.users.list.UsersListViewModel
import com.albertgf.features.users.models.UserUiMinimal

class UserListAdapter(private val viewModel: UsersListViewModel) : PagingDataAdapter<UserUiMinimal, UserViewHolder>(
    UserDiffCallBack()
) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(viewModel, it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

}

class UserDiffCallBack : DiffUtil.ItemCallback<UserUiMinimal>() {
    override fun areItemsTheSame(oldItem: UserUiMinimal, newItem: UserUiMinimal): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: UserUiMinimal, newItem: UserUiMinimal): Boolean {
        return oldItem == newItem
    }
}

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: UsersListViewModel, user: UserUiMinimal) {
        binding.viewmodel = viewModel
        binding.user = user
    }

    companion object {
        fun from(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

            return UserViewHolder(binding)
        }
    }
}