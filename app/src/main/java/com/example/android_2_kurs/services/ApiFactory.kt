package com.example.android_2_kurs.services

import com.example.android_2_kurs.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {
    private const val QUERY_API_KEY = "appid"
    private const val QUERY_LANG = "en"
    private const val QUERY_UNITS = "units"

    private val apiKeyInterceptor = Interceptor{chain ->
        var original = chain.request()
        chain.request().url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.API_KEY)
            .addQueryParameter(QUERY_UNITS, "metric")
            .addQueryParameter(QUERY_LANG, "en")
            .build().let{
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }

    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherAPI by lazy {
        retrofit.create(WeatherAPI::class.java)
    }
}