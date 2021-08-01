package com.app.postapp.domain.usecase

import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.Result

interface PostUseCase {
    suspend fun getPosts() : Result<String, List<Post>>
}