package com.mzs.core.domain.repositories

interface NetworkRepository {
    fun isConnected(): Boolean
}