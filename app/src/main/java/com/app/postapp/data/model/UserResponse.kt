package com.app.postapp.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "username")
    val username: String,
    @SerializedName(value = "email")
    val email: String
)