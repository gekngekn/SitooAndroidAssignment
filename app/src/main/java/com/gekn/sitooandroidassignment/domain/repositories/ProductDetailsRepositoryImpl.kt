package com.gekn.sitooandroidassignment.domain.repositories

import com.gekn.sitooandroidassignment.network.ApiService
import com.gekn.sitooandroidassignment.network.BaseApiResponse
import com.gekn.sitooandroidassignment.network.NetworkResult
import com.gekn.sitooandroidassignment.network.models.NetworkProductDetails
import com.gekn.sitooandroidassignment.network.models.NetworkProductImage
import com.gekn.sitooandroidassignment.network.models.NetworkSite
import com.gekn.sitooandroidassignment.network.repositiories.ProductDetailsRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * The repository extends BaseApiResponse to handle API calls.
 */
@ActivityRetainedScoped
class ProductDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ProductDetailsRepository, BaseApiResponse {

    override suspend fun getProductDetails(id: Int): NetworkResult<NetworkProductDetails?> {
        return safeApiCall { apiService.getProductDetails(id) }
    }

    override suspend fun getProductImage(id: Int): NetworkResult<NetworkProductImage?> {
        return safeApiCall { apiService.getProductImage(id) }
    }

    override suspend fun getSite(): NetworkResult<NetworkSite?> {
        return safeApiCall { apiService.getSite() }
    }

}