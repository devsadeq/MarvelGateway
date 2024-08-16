package com.example.marvelgateway.data.remote

import com.example.marvelgateway.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .addQueryParameter("ts", "1")
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_API_KEY)
            .addQueryParameter("hash", BuildConfig.MARVEL_HASH)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}