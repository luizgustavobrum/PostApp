package com.app.postapp.domain.usecase.impl

import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.Result
import com.app.postapp.domain.repository.DataRepository
import com.app.postapp.domain.usecase.PostUseCase
import javax.inject.Inject

class PostUseCaseImpl @Inject constructor(
    private val repository: DataRepository
) : PostUseCase{
    override suspend fun getPosts(): Result<String, List<Post>> = repository.getPosts()
}