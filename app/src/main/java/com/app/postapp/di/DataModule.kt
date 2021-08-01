package com.app.postapp.di

import com.app.postapp.data.repository.DataRepositoryImpl
import com.app.postapp.domain.repository.DataRepository
import com.app.postapp.domain.usecase.PostUseCase
import com.app.postapp.domain.usecase.UserUseCase
import com.app.postapp.domain.usecase.impl.PostUseCaseImpl
import com.app.postapp.domain.usecase.impl.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindPostUseCase(
        postUseCaseImpl: PostUseCaseImpl
    ): PostUseCase

    @Singleton
    @Binds
    abstract fun bindUserUseCase(
        userUseCaseImpl: UserUseCaseImpl
    ): UserUseCase

    @Singleton
    @Binds
    abstract fun bindDataRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}