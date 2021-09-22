package com.albertgf.randomusers.common.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RandomConfig private constructor(url: String, debug: Boolean) {

    val retrofit: Retrofit

    init {
        retrofit = client(url, debug)
    }

    private fun client(url: String, debug: Boolean): Retrofit {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.MINUTES)

        if (debug) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addNetworkInterceptor(interceptor)
            okHttpClient.connectTimeout(2, TimeUnit.SECONDS).readTimeout(2, TimeUnit.SECONDS)
        }

        val gson = GsonBuilder().setLenient().create()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .client(okHttpClient.build())
            .build()
    }

    companion object {
        @Volatile
        private var instance: RandomConfig? = null

        fun with(url: String, debug: Boolean): RandomConfig {
            return when {
                instance != null -> instance!!
                else -> synchronized(this) {
                    if (instance == null) instance = RandomConfig(url, debug)
                    instance!!
                }
            }
        }
    }
}