package com.gekn.sitooandroidassignment.domain

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductsResource
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.ApiService
import com.gekn.sitooandroidassignment.network.BaseApiResponse
import com.gekn.sitooandroidassignment.network.NetworkResult

class ProductsPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, ProductResource>(), BaseApiResponse {

    override fun getRefreshKey(state: PagingState<Int, ProductResource>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /**
     * Fetches the next items range from the API based on nextKey
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResource> {
        return try {
            val start = params.key ?: 0

            val response = safeApiCall { apiService.getProducts(
                    start = start,
                    num = params.loadSize
                )
            }

            Log.d("ProductsPagingSource", "Response: $response.body")

            when (response) {
                is NetworkResult.Success -> {
                    val products = response.data?.items?.toProductsResource()
                    val totalItems = response.data?.totalcount ?: 0

                    // Calculate nextKey based on totalItems
                    val nextKey = if (start + params.loadSize < totalItems) {
                        start + params.loadSize
                    } else {
                        null
                    }

                    LoadResult.Page(
                        data = products ?: emptyList(),
                        prevKey = if (start > 0) start - params.loadSize else null, // Calculate previous key
                        nextKey = nextKey
                    )
                }
                is NetworkResult.Error -> {
                    throw Exception(response.message)
                }
            }

        } catch (e: Exception) {
            Log.e("ProductsPagingSource", e.message!!)
            LoadResult.Error(e)
        }
    }

}