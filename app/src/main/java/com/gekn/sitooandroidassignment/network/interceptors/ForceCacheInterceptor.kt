package com.gekn.sitooandroidassignment.network.interceptors

import android.content.Context
import com.gekn.sitooandroidassignment.utils.Utils.hasInternetConnection
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Force cached data when no internet connection is available
 */
class ForceCacheInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder: Request.Builder = chain.request().newBuilder()
        if (!hasInternetConnection(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }

        return chain.proceed(builder.build());
    }
}