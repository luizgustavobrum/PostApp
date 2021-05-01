package com.app.postapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.databinding.LayoutPostBinding
import com.app.postapp.network.Post

class PostAdapter(
    val posts: List<Post>,
    val listener: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = LayoutPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(post = posts[position])
    }

    inner class PostViewHolder(
        binding: LayoutPostBinding,
        listener: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val userId = binding.userId
        private val id = binding.id
        private val title = binding.postTitle
        private val body = binding.postBody
        private val card = binding.cardPost

        fun bind(post: Post) {
            userId.text = post.userId.toString()
            id.text = post.id.toString()
            title.text = post.title
            body.text = post.body

            card.setOnClickListener {
                listener.invoke(post)
            }
        }
    }
}