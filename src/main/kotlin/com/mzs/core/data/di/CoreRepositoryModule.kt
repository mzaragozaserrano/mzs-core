package com.mzs.core.data.di

import com.mzs.core.data.repositories.NetworkRepositoryImpl
import com.mzs.core.domain.repositories.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreRepositoryModule {

    @Binds
    fun bindNetworkRepository(repositoryImpl: NetworkRepositoryImpl): NetworkRepository

}