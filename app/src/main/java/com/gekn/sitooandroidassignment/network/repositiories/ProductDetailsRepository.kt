package com.gekn.sitooandroidassignment.network.repositiories

import com.gekn.sitooandroidassignment.network.NetworkResult
import com.gekn.sitooandroidassignment.network.models.NetworkProductDetails
import com.gekn.sitooandroidassignment.network.models.NetworkProductImage
import com.gekn.sitooandroidassignment.network.models.NetworkSite

interface ProductDetailsRepository {
    suspend fun getProductDetails(id: Int): NetworkResult<NetworkProductDetails?>
    suspend fun getProductImage(id: Int): NetworkResult<NetworkProductImage?>
    suspend fun getSite(): NetworkResult<NetworkSite?>
}