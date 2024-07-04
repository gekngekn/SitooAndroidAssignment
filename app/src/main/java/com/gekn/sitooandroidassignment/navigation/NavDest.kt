package com.gekn.sitooandroidassignment.navigation

import androidx.annotation.StringRes
import com.gekn.sitooandroidassignment.R

sealed class NavDest(
    val route: String,
    @StringRes val resourceId: Int
) {
    data object Products : NavDest("Products", R.string.screen_products)

    data object ProductDetails : NavDest("ProductDetails", R.string.screen_product_details)
}