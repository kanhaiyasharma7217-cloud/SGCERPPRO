package com.shreeganpati.sgcerppro.utils

import android.content.Context

class TokenManager(context: Context) {

    private val pref =
        context.getSharedPreferences(
            "SGC_PREF",
            Context.MODE_PRIVATE
        )

    fun saveToken(token: String) {

        pref.edit()
            .putString("TOKEN", token)
            .apply()

    }

    fun getToken(): String {

        return pref.getString(
            "TOKEN",
            ""
        ) ?: ""

    }

    fun clear() {

        pref.edit().clear().apply()

    }

}