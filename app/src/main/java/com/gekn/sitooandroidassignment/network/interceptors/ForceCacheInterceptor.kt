package com.gekn.sitooandroidassignment.network.interceptors

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import com.gekn.sitooandroidassignment.utils.Utils.hasInternetConnection

class ForceCacheInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!hasInternetConnection(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}