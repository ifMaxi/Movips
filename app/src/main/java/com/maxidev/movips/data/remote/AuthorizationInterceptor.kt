package com.maxidev.movips.data.remote

import com.maxidev.movips.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {

    private val authorization = BuildConfig.AUTHORIZATION

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $authorization")
            .build()

        return chain.proceed(request)
    }
}