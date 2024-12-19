package com.mzs.core.domain.di

import com.mzs.core.domain.utils.generic.DateUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreUtilsModule {
    @Provides
    fun provideDateUtils(): DateUtils = DateUtils()
}