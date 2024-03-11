package com.thecocktailapp.core.data.di

import com.thecocktailapp.core.domain.repositories.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreRepositoryModule {

    @Binds
    fun bindNetworkRepository(repositoryImpl: com.thecocktailapp.core.data.repositories.NetworkRepositoryImpl): NetworkRepository

}