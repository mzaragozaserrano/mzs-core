package com.thecocktailapp.core.domain.repositories

interface NetworkRepository {
    fun isConnected(): Boolean
}