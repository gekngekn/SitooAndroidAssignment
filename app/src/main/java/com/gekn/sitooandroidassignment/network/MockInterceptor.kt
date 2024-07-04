package com.gekn.sitooandroidassignment.network

import android.content.Context
import android.util.Log
import com.gekn.sitooandroidassignment.utils.readJSONFromAssets
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * MockInterceptor to handle requests and responses for testing purposes.
 */
class MockInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (chain.request().url.toUri().toString().startsWith("https://www.mocky.io/v2/sites/1/products.json")) {
            Log.d("MockInterceptor", "Received request for products.json")
            val json = readJSONFromAssets(context, "mockProductsData.json")

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(json)
                .body(
                    json.toByteArray()
                        .toResponseBody("application/json".toMediaType())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {

            // Continue handling the request
            return chain.proceed(chain.request())
        }
    }
}