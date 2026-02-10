package com.example.vkauth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object VkAuthManager {

    // Создайте приложение в VK и вставьте данные ниже.
    private const val VK_CLIENT_ID = "ВАШ_VK_APP_ID"

    private const val REDIRECT_URI = "vk$VK_CLIENT_ID://oauth.vk.com/blank.html"
    private const val AUTH_URL = "https://oauth.vk.com/authorize"

    fun startAuth(context: Context) {
        val authUri = Uri.parse(AUTH_URL).buildUpon()
            .appendQueryParameter("client_id", VK_CLIENT_ID)
            .appendQueryParameter("display", "mobile")
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("scope", "email")
            .appendQueryParameter("response_type", "token")
            .appendQueryParameter("v", "5.199")
            .build()

        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, authUri)
    }

    fun parseAuthResponse(intent: Intent?): String? {
        val data = intent?.data ?: return null

        // VK возвращает access_token во фрагменте URL.
        val fragment = data.fragment ?: return null
        val token = fragment.split("&")
            .firstOrNull { it.startsWith("access_token=") }
            ?.substringAfter("access_token=")

        return token
    }
}
