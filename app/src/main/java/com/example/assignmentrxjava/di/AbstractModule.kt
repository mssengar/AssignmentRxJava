package com.example.assignmentrxjava.di

import com.example.assignmentrxjava.data.datasource.RemoteSource
import com.example.assignmentrxjava.data.datasource.RemoteSourceImpl
import com.example.assignmentrxjava.domain.Repository
import com.example.assignmentrxjava.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource
}