package com.mzaragozaserrano.domain.repositories

interface NetworkRepository {
    fun isConnected(): Boolean
}