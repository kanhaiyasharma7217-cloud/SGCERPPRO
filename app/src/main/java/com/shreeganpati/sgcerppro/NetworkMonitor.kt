package com.shreeganpati.sgcerppro.network

import android.content.Context
import android.net.ConnectivityManager

object NetworkMonitor {

    fun isConnected(
        context: Context
    ): Boolean {

        val manager =
            context.getSystemService(
                ConnectivityManager::class.java
            )

        return manager.activeNetwork != null

    }

}