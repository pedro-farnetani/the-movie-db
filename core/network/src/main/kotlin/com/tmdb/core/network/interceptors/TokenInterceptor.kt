package com.tmdb.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTH_HEADER = "Authorization"
private const val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NTAzZGFjMjdlOWQyZmE5ZDA0NGI4MGNkYjkxMGNlOSIsIm5iZiI6MTY1MjUzNDU3MS4zNjgsInN1YiI6IjYyN2ZhZDJiYzkyYzVkMDA5ZDQwZDUwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.av3p0B_FEI7tVxJN9Hi2pjhWmcZdsH2vtxkuxKRamFU"

internal class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain
            .request()
            .newBuilder()

        requestBuilder.addHeader(AUTH_HEADER, "Bearer $token")

        return chain.proceed(requestBuilder.build())
    }
}
