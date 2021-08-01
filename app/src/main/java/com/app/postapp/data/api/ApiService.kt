package com.app.postapp.data.api

import com.app.postapp.data.model.PostResponse
import com.app.postapp.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApp {
    @GET(value = "/posts")
    suspend fun posts(): Response<List<PostResponse>>

    @GET(value = "/users")
    suspend fun users(): Response<List<UserResponse>>
}
