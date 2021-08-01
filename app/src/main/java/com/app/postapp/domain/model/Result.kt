package com.app.postapp.domain.model

sealed class Result<out L, out R> {
    data class Error<out L>(val value: L) : Result<L, Nothing>()
    data class Success<out R>(val value: R) : Result<Nothing, R>()
}