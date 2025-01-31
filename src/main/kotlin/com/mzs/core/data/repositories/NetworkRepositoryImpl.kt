package com.mzs.core.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.mzs.core.domain.repositories.NetworkRepository

class NetworkRepositoryImpl(private val context: Context) : NetworkRepository {

    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities != null
    }

}