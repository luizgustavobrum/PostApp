package com.app.postapp.domain.repository

import com.app.postapp.domain.model.Result
import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.User

interface DataRepository {
    suspend fun getPosts(): Result<String, List<Post>>
    suspend fun getUsers(): Result<String, List<User>>
}