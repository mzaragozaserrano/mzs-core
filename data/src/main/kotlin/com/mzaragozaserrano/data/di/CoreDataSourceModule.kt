package com.mzaragozaserrano.data.di

import com.mzaragozaserrano.data.datasources.local.ResourcesDataSource
import com.mzaragozaserrano.data.datasources.local.ResourcesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreDataSourceModule {

    @Binds
    fun bindResourcesDataSource(dataSourceImpl: ResourcesDataSourceImpl): ResourcesDataSource

}