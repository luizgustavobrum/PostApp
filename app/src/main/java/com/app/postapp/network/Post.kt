package com.app.postapp.network

import com.google.gson.annotations.SerializedName

data class Post(
        @SerializedName(value = "userId")
        val userId: Int,
        @SerializedName(value = "id")
        val id: Int,
        @SerializedName(value = "title")
        val title: String,
        @SerializedName(value = "body")
        val body: String
)