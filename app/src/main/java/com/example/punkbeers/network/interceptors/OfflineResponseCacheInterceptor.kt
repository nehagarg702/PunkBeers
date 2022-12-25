package com.example.punkbeers.network.interceptors

import com.example.punkbeers.utils.UtilityMethods
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response

/****
 * Class for caching the data for four weeks
 */
@Module
class OfflineResponseCacheInterceptor : Interceptor {

    @Provides
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!UtilityMethods.isNetworkAvailable) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 2419200)
                    .build()
        }
        return chain.proceed(request)
    }
}