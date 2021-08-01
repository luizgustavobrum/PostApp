package com.app.postapp.domain.usecase

import com.app.postapp.domain.model.Result
import com.app.postapp.domain.model.User

interface UserUseCase {
    suspend fun getUsers(): Result<String, List<User>>
}