package com.app.postapp.domain.repository

import com.app.postapp.domain.model.Either
import com.app.postapp.data.model.PostResponse
import com.app.postapp.data.model.UserResponse

interface RepositoryInterface {
    suspend fun getPostsEither(): Either<String, List<PostResponse>>
    suspend fun getUsersEither(): Either<String, List<UserResponse>>
}