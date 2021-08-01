package com.app.postapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.postapp.domain.model.Post
import com.app.postapp.domain.model.Result
import com.app.postapp.domain.model.User
import com.app.postapp.domain.usecase.PostUseCase
import com.app.postapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _uiPostsState: MutableStateFlow<UiState<String, List<Post>>> =
        MutableStateFlow(UiState.Loading)
    val uiPostsState: StateFlow<UiState<String, List<Post>>> get() = _uiPostsState

    private val _uiUsersState: MutableStateFlow<UiState<String, List<User>>> =
        MutableStateFlow(UiState.Loading)
    val uiUsersState: StateFlow<UiState<String, List<User>>> get() = _uiUsersState

    init {
        getPosts()
        getUsers()
    }

    fun getPosts() = viewModelScope.launch {
        _uiPostsState.value = UiState.Loading
        val posts = postUseCase.getPosts()
        when (posts) {
            is Result.Success -> {
                _uiPostsState.value = UiState.Success(posts.value)
            }
            is Result.Error -> {
                _uiPostsState.value = UiState.Error(posts.value)
            }
        }

    }


    fun getUsers() = viewModelScope.launch {
        _uiUsersState.value = UiState.Loading
        val users = userUseCase.getUsers()
        when (users) {
            is Result.Success -> {
                _uiUsersState.value = UiState.Success(users.value)
            }
            is Result.Error -> {
                _uiUsersState.value = UiState.Error(users.value)
            }
        }
    }

}