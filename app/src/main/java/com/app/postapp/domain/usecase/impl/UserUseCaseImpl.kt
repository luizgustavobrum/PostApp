package com.app.postapp.domain.usecase.impl

import com.app.postapp.domain.model.Result
import com.app.postapp.domain.model.User
import com.app.postapp.domain.repository.DataRepository
import com.app.postapp.domain.usecase.UserUseCase
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: DataRepository
) : UserUseCase {
    override suspend fun getUsers(): Result<String, List<User>> {
        return repository.getUsers()
    }
}