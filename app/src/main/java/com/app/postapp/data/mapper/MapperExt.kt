package com.app.postapp.data.mapper

import com.app.postapp.data.model.PostResponse
import com.app.postapp.data.model.UserResponse
import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.User

fun PostResponse.toPost() = Post(
    userId = this.userId,
    id = this.id,
    title = this.title,
    body = this.body
)

fun UserResponse.toUser() = User(
    id = this.id,
    name = this.name,
    username = this.username,
    email = this.email
)