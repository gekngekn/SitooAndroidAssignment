package com.gekn.sitooandroidassignment.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gekn.sitooandroidassignment.domain.ProductsPagingSource
import com.gekn.sitooandroidassignment.network.ApiService
import com.gekn.sitooandroidassignment.network.repositiories.ProductsPagingRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductsPagingPagingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ProductsPagingRepository {

    override fun getProductsPaging() = Pager(
        config =  PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 3
        ),
        pagingSourceFactory = {
            ProductsPagingSource(apiService)
        }
    ).flow

}