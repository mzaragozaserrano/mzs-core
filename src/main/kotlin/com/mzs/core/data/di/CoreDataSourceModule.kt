package com.mzs.core.data.di

import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.mzs.core.data.datasources.local.ResourcesDataSourceImpl
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