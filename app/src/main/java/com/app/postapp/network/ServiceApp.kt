package com.app.postapp.network

import retrofit2.Response
import retrofit2.http.GET

interface ServiceApp {
    @GET(value = "/posts")
    suspend fun posts(): Response<List<Post>>

    @GET(value = "/users")
    suspend fun users(): Response<List<User>>
}
