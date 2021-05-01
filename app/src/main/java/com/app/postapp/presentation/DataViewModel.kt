package com.app.postapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.postapp.components.Either
import com.app.postapp.network.Post
import com.app.postapp.network.User
import com.app.postapp.repository.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(
    private val repository: RepositoryInterface
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>> = _postsLiveData

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> = _usersLiveData


    init {
        viewModelScope.launch {
            val posts = repository.getPostsEither()
            if (posts is Either.Right) {
                posts.value.let { _postsLiveData.postValue(it) }
            }

            val users = repository.getUsersEither()
            if (users is Either.Right) {
                _usersLiveData.postValue(users.value!!)
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = withContext(Dispatchers.IO) { repository.getUsers() }
            users.fold(
                onSuccess = {
                    _usersLiveData.postValue(it)
                },
                onFailure = {

                })
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            val posts = withContext(Dispatchers.IO) { repository.getPosts() }
            posts.fold(
                onSuccess = {
                    _postsLiveData.postValue(it)
                },
                onFailure = {

                })
        }
    }


    fun getPostsWithEither() {
        viewModelScope.launch {
            val posts = repository.getPostsEither()
            if (posts is Either.Right) {
                posts.value.let { _postsLiveData.postValue(it) }
            }
        }
    }


    fun getUsersWithEiter() {
        viewModelScope.launch {
            val users = repository.getUsersEither()
            if (users is Either.Right) {
                _usersLiveData.postValue(users.value!!)
            }
        }
    }
}