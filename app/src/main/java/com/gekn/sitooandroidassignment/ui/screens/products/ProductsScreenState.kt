package com.gekn.sitooandroidassignment.ui.screens.products

import androidx.paging.PagingData
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.ui.AppState
import kotlinx.coroutines.flow.Flow

data class ProductsScreenState(
    val appState: AppState,
    val products: Flow<PagingData<ProductResource>>,
)