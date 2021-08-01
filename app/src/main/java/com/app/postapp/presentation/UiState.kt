package com.app.postapp.presentation

sealed class UiState<out L, out R> {
    object Initial : UiState<Nothing, Nothing>()
    object Loading : UiState<Nothing, Nothing>()
    data class Success<R>(val success: R) : UiState<Nothing, R>()
    data class Error<L>(val error: L) : UiState<L, Nothing>()
}
