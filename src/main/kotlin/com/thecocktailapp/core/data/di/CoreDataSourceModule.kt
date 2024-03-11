package com.thecocktailapp.core.data.di

import com.thecocktailapp.core.data.datasources.local.ResourcesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreDataSourceModule {

    @Binds
    fun bindResourcesDataSource(dataSourceImpl: ResourcesDataSourceImpl): com.thecocktailapp.core.data.datasources.local.ResourcesDataSource

}