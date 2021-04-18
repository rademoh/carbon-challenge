package com.rabiu.carbonchallenge.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * A {@see RequestInterceptor} that adds an appId to requests
 */
class AuthInterceptor(private val appId: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().
                addHeader("app-id", "$appId").build()
        return chain.proceed(request)
    }


}
