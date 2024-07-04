package com.gekn.sitooandroidassignment

import com.gekn.sitooandroidassignment.network.models.NetworkProduct

class ProductFactory {

    fun getTestProducts(size: Int): List<NetworkProduct> {
        return (1..size).map { getTestProduct(it) }
    }

    private fun getTestProduct(suffix: Int) : NetworkProduct {
        return NetworkProduct(
            productid = suffix,
            title = "Product $suffix",
            moneyprice = "10.0"
        )
    }

}