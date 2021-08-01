package com.app.postapp.data.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName(value = "userId")
    val userId: Int,
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "body")
    val body: String
)