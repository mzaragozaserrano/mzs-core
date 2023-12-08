package com.mzaragozaserrano.data.di

import com.mzaragozaserrano.data.repositories.NetworkRepositoryImpl
import com.mzaragozaserrano.domain.repositories.NetworkRepository
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