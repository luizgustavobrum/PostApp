package com.app.postapp.data.repository

import android.util.Log
import com.app.postapp.domain.model.Either
import com.app.postapp.data.model.PostResponse
import com.app.postapp.data.api.ServicePlataform
import com.app.postapp.data.model.UserResponse
import com.app.postapp.domain.repository.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

internal class DataRepository : RepositoryInterface {

    override suspend fun getPosts(): Result<List<PostResponse>> {
        return try {
            val response = ServicePlataform.serviceApp.posts()
            if (response.isSuccessful && response.body() != null) {
                Result.success(value = response.body()!!)
            } else {
                Result.failure(exception = Exception("HttpCode: ${response.code()} - body: ${response.body()}"))
            }

        } catch (e: Exception) {
            prettyLog(e)
            Result.failure(exception = e)
        }
    }

    override suspend fun getUsers(): Result<List<UserResponse>> {
        return try {
            val response = ServicePlataform.serviceApp.users()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(exception = Exception("HttpCode: ${response.code()} - body: ${response.body()}"))
            }

        } catch (e: Exception) {
            prettyLog(e)
            Result.failure(exception = e)
        }
    }

    override suspend fun getPostsEither(): Either<String, List<PostResponse>> =
        withContext(Dispatchers.IO) {
            try {
                val response = ServicePlataform.serviceApp.posts()
                if (response.isSuccessful && response.body() != null) {
                    Either.Right(response.body().orEmpty())
                } else {
                    prettyLog(msg = "Code: ${response.code()} - Response: ${response.body()}")
                    Either.Left(value = "Code: ${response.code()} - Response: ${response.body()}")
                }
            } catch (e: HttpException) {
                val error = "Code:${e.code()} - Response: ${e.response()} - Message: ${e.message()}"
                prettyLog(e)
                Either.Left(value = error)
            } catch (e: Exception) {
                prettyLog(e)
                Either.Left("Erro: ${e.printStackTrace()}")
            }
        }

    override suspend fun getUsersEither(): Either<String, List<UserResponse>> =
        withContext(Dispatchers.IO) {
            try {
                val response = ServicePlataform.serviceApp.users()
                prettyLog(msg = "USERS SUCCESS")
                if (response.isSuccessful && response.body() != null) {
                    Either.Right(response.body().orEmpty())
                } else {
                    prettyLog(msg = "Code:${response.code()} - Response: ${response.body()}")
                    Either.Left(value = "Code:${response.code()} - Response: ${response.body()}")
                }
            } catch (e: HttpException) {
                val error = "Code:${e.code()} - Response: ${e.response()} - Message: ${e.message()}"
                prettyLog(e)
                Either.Left(value = error)
            } catch (e: Exception) {
                prettyLog(e)
                Either.Left("Erro: ${e.printStackTrace()}")
            }
        }

    private fun prettyLog(e: Exception) {
        Log.e(TAG, e.toString())
    }

    private fun prettyLog(msg: String) {
        Log.e(TAG, msg)
    }

    companion object {
        private val TAG = DataRepository::class.java.simpleName
    }
}