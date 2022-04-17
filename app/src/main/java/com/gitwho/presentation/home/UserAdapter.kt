package com.gitwho.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gitwho.common.OnRecyclerItemClickCallback
import com.gitwho.databinding.UserItemBinding
import com.gitwho.domain.model.User

class UserAdapter(val callback: OnRecyclerItemClickCallback<User>) :
    ListAdapter<User, UserAdapter.ViewHolder>(DiffTypeCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = getItem(position)
        holder.apply {
            bind(city)
            itemView.tag = city
        }
    }

    inner class ViewHolder(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(content: User) {
            binding.apply {
                user = content
                itemView.setOnClickListener {
                    callback.onRecyclerItemClicked(adapterPosition, itemView, content)
                }
                executePendingBindings()
            }
        }
    }
}

private class DiffTypeCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem.id == newItem.id
    }
}