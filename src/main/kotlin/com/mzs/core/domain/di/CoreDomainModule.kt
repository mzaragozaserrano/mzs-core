package com.mzs.core.domain.di

import com.mzs.core.domain.utils.generic.DateUtils
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDomainModule = module {
    // Utils
    singleOf(::DateUtils)
}