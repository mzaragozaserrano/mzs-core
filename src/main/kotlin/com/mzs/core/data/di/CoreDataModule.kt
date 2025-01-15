package com.mzs.core.data.di

import com.mzs.core.data.datasources.local.ResourcesDataSource
import com.mzs.core.data.datasources.local.ResourcesDataSourceImpl
import com.mzs.core.data.repositories.NetworkRepositoryImpl
import com.mzs.core.data.utils.encryption.EncryptedPreferences
import com.mzs.core.data.utils.encryption.EncryptionServices
import com.mzs.core.data.utils.encryption.KeyStoreWrapper
import com.mzs.core.domain.repositories.NetworkRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule = module {
    // DataSources
    singleOf(::ResourcesDataSourceImpl) { bind<ResourcesDataSource>() }
    // Repositories
    singleOf(::NetworkRepositoryImpl) { bind<NetworkRepository>() }
    // Utils
    singleOf(::EncryptedPreferences)
    singleOf(::EncryptionServices)
    singleOf(::KeyStoreWrapper)
}