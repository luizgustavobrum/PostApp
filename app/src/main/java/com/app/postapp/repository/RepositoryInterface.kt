package com.app.postapp.repository

import com.app.postapp.components.Either
import com.app.postapp.network.Post
import com.app.postapp.network.User

interface RepositoryInterface {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getUsers(): Result<List<User>>

    suspend fun getPostsEither(): Either<String, List<Post>>
    suspend fun getUsersEither(): Either<String, List<User>>
}