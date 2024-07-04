package com.gekn.sitooandroidassignment.network

import com.gekn.sitooandroidassignment.network.models.NetworkProductDetails
import com.gekn.sitooandroidassignment.network.models.NetworkProductImage
import com.gekn.sitooandroidassignment.network.models.NetworkProducts
import com.gekn.sitooandroidassignment.network.models.NetworkSite
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("sites/1/products.json?&fields=productid,title,moneyprice")
    suspend fun getProducts(@Query("start") start: Int, @Query("num") num: Int): Response<NetworkProducts>

    @GET("sites/1/products/{id}.json")
    suspend fun getProductDetails(@Path("id") id: Int): Response<NetworkProductDetails>

    @GET("sites/1/products/{id}/images/0.json")
    suspend fun getProductImage(@Path("id") id: Int): Response<NetworkProductImage>

    @GET("sites/1.json")
    suspend fun getSite(): Response<NetworkSite>
}