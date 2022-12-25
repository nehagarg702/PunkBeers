package com.example.punkbeers.di.module

import android.content.Context
import com.example.punkbeers.network.interceptors.OfflineResponseCacheInterceptor
import com.example.punkbeers.network.interceptors.ResponseCacheInterceptor
import com.example.punkbeers.di.qualifiers.ApplicationContext
import com.example.punkbeers.di.qualifiers.LoggingInterceptorQualifier
import com.example.punkbeers.di.qualifiers.OfflineCacheQualifier
import com.example.punkbeers.di.qualifiers.ResponseCacheQualifier
import com.example.punkbeers.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [OfflineResponseCacheInterceptor::class, ResponseCacheInterceptor::class, ContextModule::class])
class OkHttpClientModule {

    @Provides
    @LoggingInterceptorQualifier
    fun getLoggingInterceptor(): Interceptor
    {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun getOkHttpClient(@LoggingInterceptorQualifier logging : Interceptor, @ResponseCacheQualifier response: Interceptor,
                        @OfflineCacheQualifier offlineResponse : Interceptor, cache:Cache): OkHttpClient
    {
        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(response)
        httpClient.addInterceptor(offlineResponse)
        httpClient.cache(cache)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    fun getCache(file :File): Cache
    {
        return Cache(file, 10 * 1024 * 1024)
    }

    @Provides
    @ApplicationScope
    fun getFile(@ApplicationContext context:Context) :File{
        return File(context.cacheDir, "ResponsesCache")
    }

    @Provides
    @ResponseCacheQualifier
    fun getNetworkResponseInterceptor():Interceptor
    {
        return ResponseCacheInterceptor()
    }

    @Provides
    @OfflineCacheQualifier
    fun getOfflineCacheResponseInterceptor():Interceptor
    {
        return OfflineResponseCacheInterceptor()
    }
}