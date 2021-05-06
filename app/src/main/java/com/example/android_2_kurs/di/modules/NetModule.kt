package com.example.android_2_kurs.di.modules

import com.example.android_2_kurs.BuildConfig
import com.example.android_2_kurs.data.api.LoggingInterceptor
import com.example.android_2_kurs.data.api.WeatherAPI
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val QUERY_API_KEY = "appid"
private const val QUERY_LANG = "en"
private const val QUERY_UNITS = "units"

@Module
class NetModule {

    @Provides
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory,
        @Named("baseUrl") baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .build()

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = BuildConfig.API_ENDPOINT


    @Provides
    @Named("apiInterceptor")
    fun provideApiKeyInterceptor(): Interceptor = Interceptor{chain ->
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

    @Provides
    fun provideClient(
        @Named("apiInterceptor")apiKeyInterceptor: Interceptor,
        @Named("loggingInterceptor")loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()


    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor()


    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

}