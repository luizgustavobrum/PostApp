package com.app.postapp.data.repository

import android.util.Log
import com.app.postapp.data.api.ApiService
import com.app.postapp.domain.model.Result
import com.app.postapp.data.mapper.toPost
import com.app.postapp.data.mapper.toUser
import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.User
import com.app.postapp.domain.repository.DataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val service: ApiService
) : DataRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getPosts(): Result<String, List<Post>> = withContext(ioDispatcher) {
        try {
            val response = service.posts()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { postsResponse ->
                    val posts = postsResponse.map {
                        it.toPost()
                    }
                    Result.Success(posts)
                } ?: kotlin.run {
                    Result.Error(value = "Code: ${response.code()} - Response: ${response.body()}")
                }

            } else {
                prettyLog(msg = "Code: ${response.code()} - Response: ${response.body()}")
                Result.Error(value = "Code: ${response.code()} - Response: ${response.body()}")
            }
        } catch (e: HttpException) {
            val error = "Code:${e.code()} - Response: ${e.response()} - Message: ${e.message()}"
            prettyLog(e)
            Result.Error(value = error)
        } catch (e: Exception) {
            prettyLog(e)
            Result.Error("Erro: ${e.printStackTrace()}")
        }

    }


    override suspend fun getUsers(): Result<String, List<User>> = withContext(ioDispatcher) {
        try {
            val response = service.users()
            prettyLog(msg = "USERS SUCCESS")
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { usersResponse ->
                    val users = usersResponse.map {
                        it.toUser()
                    }
                    Result.Success(users)
                } ?: kotlin.run {
                    prettyLog(msg = "Code: ${response.code()} - Response: ${response.body()}")
                    Result.Error(value = "Code: ${response.code()} - Response: ${response.body()}")
                }
            } else {
                prettyLog(msg = "Code:${response.code()} - Response: ${response.body()}")
                Result.Error(value = "Code:${response.code()} - Response: ${response.body()}")
            }
        } catch (e: HttpException) {
            val error = "Code:${e.code()} - Response: ${e.response()} - Message: ${e.message()}"
            prettyLog(e)
            Result.Error(value = error)
        } catch (e: Exception) {
            prettyLog(e)
            Result.Error("Erro: ${e.printStackTrace()}")
        }
    }

    private fun prettyLog(e: Exception) {
        Log.e(TAG, e.toString())
    }

    private fun prettyLog(msg: String) {
        Log.e(TAG, msg)
    }

    companion object {
        private val TAG = DataRepositoryImpl::class.java.simpleName
    }
}