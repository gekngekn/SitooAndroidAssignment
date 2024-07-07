package com.gekn.sitooandroidassignment

import androidx.paging.PagingConfig
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductResource
import com.gekn.sitooandroidassignment.domain.ProductsPagingSource
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.ApiService
import com.gekn.sitooandroidassignment.network.BaseApiResponse
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ProductsPagingSourceTest : BaseApiResponse {

    @MockK
    private lateinit var mockApiService: ApiService
    private lateinit var pagingSource: ProductsPagingSource

    private val productFactory = ProductFactory()
    private val pageLoadSize = 1
    private val totalSize = 33
    private val productList = productFactory.getTestProducts(totalSize)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pagingSource = ProductsPagingSource(mockApiService)
    }

    @Test
    fun `given state has no anchor position, refresh key returns null`() {
        val state = PagingState<Int, ProductResource>(emptyList(), null, PagingConfig(0), 0)
        assertNull(pagingSource.getRefreshKey(state))
    }

    @Test
    fun `given state has anchor position but no pages, refresh key returns null`() {
        val state = PagingState<Int, ProductResource>(emptyList(), 1, PagingConfig(0), 0)
        assertNull(pagingSource.getRefreshKey(state))
    }

    @Test
    fun `given state has anchor position and pages, refresh key returns value`() {
        val page = LoadResult.Page(productList.map { it.toProductResource() }, 0, null)
        val state = PagingState(listOf(page), 1, PagingConfig(0), 0)
        assertEquals(1, pagingSource.getRefreshKey(state))
    }

}