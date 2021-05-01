package com.app.postapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.databinding.LayoutUserBinding
import com.app.postapp.network.User

class UserAdapter(
    val users: List<User>,
    val listener: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = LayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(user = users[position])
    }

    class UserViewHolder(binding: LayoutUserBinding, listener: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private val name = binding.userName
        private val username = binding.userUsername
        private val email = binding.userEmail
        private val cardViewUser = binding.cardUser
        private val listener = listener

        fun bind(user: User) {
            name.text = user.name
            username.text = user.username
            email.text = user.email
            cardViewUser.setOnClickListener {
                listener.invoke(user)
            }
        }
    }
}