package com.gekn.sitooandroidassignment.network.repositiories

import androidx.paging.PagingData
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import kotlinx.coroutines.flow.Flow

interface ProductsPagingRepository {
    fun getProductsPaging(): Flow<PagingData<ProductResource>>
}