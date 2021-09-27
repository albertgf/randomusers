package com.albertgf.randomusers.features.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.databinding.ItemUserBinding

class UserListAdapter : PagingDataAdapter<UserUi, UserViewHolder>(UserDiffCallBack()) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

}

class UserDiffCallBack : DiffUtil.ItemCallback<UserUi>() {
    override fun areItemsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem == newItem
    }
}

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserUi) {
        binding.name.text = user.name
    }

    companion object {
        fun from(parent: ViewGroup) : UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

            return UserViewHolder(binding)
        }
    }
    }