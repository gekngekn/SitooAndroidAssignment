package com.gekn.sitooandroidassignment

import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductDetailsResource
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductResource
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductsResource
import com.gekn.sitooandroidassignment.domain.models.ProductDetailsResource
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.models.NetworkProduct
import com.gekn.sitooandroidassignment.network.models.NetworkProductDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductsMapperTest {

    @Test
    fun `test toProductsResource maps correctly`() {
        val networkProducts = listOf(
            NetworkProduct(productid = 1, title = "Product 1", moneyprice = "10.0"),
            NetworkProduct(productid = 2, title = "Product 2", moneyprice = "20.0")
        )
        val expectedProducts = listOf(
            ProductResource(id = 1, name = "Product 1", price = "10.0"),
            ProductResource(id = 2, name = "Product 2", price = "20.0")
        )

        val actualProducts = networkProducts.toProductsResource()

        assertEquals(expectedProducts, actualProducts)
    }

    @Test
    fun `test toProductResource maps correctly`() {
        val networkProduct = NetworkProduct(productid = 1, title = "Product 1", moneyprice = "10.0")
        val expectedProduct = ProductResource(id = 1, name = "Product 1", price = "10.0")

        val actualProduct = networkProduct.toProductResource()

        assertEquals(expectedProduct, actualProduct)
    }

    @Test
    fun `test toProductDetailsResource maps correctly`() {
        val networkProductDetails = NetworkProductDetails(
            productid = 1,
            sku = "SKU123",
            skumanufacturer = "Manufacturer",
            descriptionshort = "Short description",
            description = "Long description",
            descriptionhtml = "<p>HTML description</p>",
            deliverystatus = "In stock",
            moneyprice = "10.0",
            moneypriceorg = "12.0",
            moneypricein = "8.0",
            unitlabel = "pcs",
            allowdecimals = true,
            deliveryinfo = "Free delivery",
            externalinputtitle = "External title",
            offerisenabled = true,
            moneyofferprice = "8.0",
            offertitle = "Special offer",
            offerdatestart = null,
            offerdateend = null,
            active = true,
            activepos = true,
            vatid = 12345,
            deliveryclassid = 1,
            defaultcategoryid = null,
            categories = arrayListOf(1, 2, 3),
            manufacturerid = null,
            manufacturerurl = "https://www.manufacturer.com",
            custom1 = "Custom 1",
            custom2 = "Custom 2",
            custom3 = "Custom 3",
            custom4 = "Custom 4",
            custom5 = "Custom 5",
            stockcountenable = true,
            stockallowbackorder = true,
            variantparentid = null,
            barcode = null,
            barcodealiases = arrayListOf("9876543210", "5645454"),
            similar = arrayListOf(1, 2),
            related = arrayListOf(3, 4),
            accessories = arrayListOf(5, 6),
            offerisactive = true,
            moneyfinalprice = "8.0",
            vatvalue = null,
            productgrouptype = null,
            pricelisthasvolume = true,
            variant = null,
            customattributes = null,
            friendly = "Friendly name",
            seoTitle = "SEO Title",
            seoKeywords = "SEO, Keywords",
            seoDescription = "SEO Description",
            title = "Product Title",
            datecreated = 1678819200,
            datemodified = 1681392000
        )
        val imageUrl = "https://example.com/image.jpg"

        val expectedProductDetails = ProductDetailsResource(
            productId = 1,
            sku = "SKU123",
            skuManufacturer = "Manufacturer",
            descriptionShort = "Short description",
            description = "Long description",
            descriptionHtml = "<p>HTML description</p>",
            deliveryStatus = "In stock",
            moneyPrice = "10.0",
            moneyPriceOrg = "12.0",
            moneyPriceIn = "8.0",
            unitLabel = "pcs",
            allowDecimals = true,
            deliveryInfo = "Free delivery",
            externalInputTitle = "External title",
            offerIsEnabled = true,
            moneyOfferPrice = "8.0",
            offerTitle = "Special offer",
            offerDateStart = 0,
            offerDateEnd = 0,
            active = true,
            activePos = true,
            vatId = 12345,
            deliveryClassId = 1,
            defaultCategoryId = 0,
            categories = listOf(1, 2, 3),
            manufacturerId = "",
            manufacturerUrl = "https://www.manufacturer.com",
            custom1 = "Custom 1",
            custom2 = "Custom 2",
            custom3 = "Custom 3",
            custom4 = "Custom 4",
            custom5 = "Custom 5",
            stockCountEnable = true,
            stockAllowBackOrder = true,
            variantParentId = 0,
            barcode = "",
            barcodeAliases = listOf("9876543210", "5645454"),
            similar = listOf(1, 2),
            related = listOf(3, 4),
            accessories = listOf(5, 6),
            offerIsActive = true,
            moneyFinalPrice = "8.0",
            vatValue = 0,
            productGroupType = 0,
            priceListHasVolume = true,
            variant = emptyList(),
            customAttributes = "",
            friendly = "Friendly name",
            seoTitle = "SEO Title",
            seoKeywords = "SEO, Keywords",
            seoDescription = "SEO Description",
            title = "Product Title",
            dateCreated = 1678819200,
            dateModified = 1681392000,
            imgUrl = imageUrl
        )

        val actualProductDetails = networkProductDetails.toProductDetailsResource(imageUrl)

        assertEquals(expectedProductDetails, actualProductDetails)
    }
}