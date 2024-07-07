package com.gekn.sitooandroidassignment.network.di

import android.content.Context
import com.gekn.sitooandroidassignment.BuildConfig
import com.gekn.sitooandroidassignment.network.ApiService
import com.gekn.sitooandroidassignment.network.interceptors.BasicAuthInterceptor
import com.gekn.sitooandroidassignment.network.interceptors.CacheInterceptor
import com.gekn.sitooandroidassignment.network.interceptors.ForceCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = " https://api-sandbox.mysitoo.com/v2/accounts/90316/"

    // Logging Interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(BasicAuthInterceptor(BuildConfig.API_USER, BuildConfig.API_PASS))
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(context))
            .cache(Cache(File(context.cacheDir, "http-cache"), 5L * 1024L * 1024L)) // 5mb
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}