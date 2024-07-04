package com.gekn.sitooandroidassignment.ui.screens.productdetails

import com.gekn.sitooandroidassignment.domain.models.ProductDetailsResource
import com.gekn.sitooandroidassignment.ui.AppState

data class ProductDetailsScreenState(
    val appState: AppState,
    val product: ProductDetailsResource?,
    val getProductDetails: (id: Int) -> Unit
)