package com.gekn.sitooandroidassignment.domain

import com.gekn.sitooandroidassignment.domain.models.ProductDetailsResource
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.models.NetworkProduct
import com.gekn.sitooandroidassignment.network.models.NetworkProductDetails

object ProductsMapper {

    fun List<NetworkProduct>.toProductsResource(): List<ProductResource> {
        return this.map {
            it.toProductResource()
        }
    }

    fun NetworkProduct.toProductResource(): ProductResource {
        return ProductResource(
            id = this.productid,
            name = this.title,
            price = this.moneyprice
        )
    }

    fun NetworkProductDetails.toProductDetailsResource(imgUrl: String?): ProductDetailsResource {
        return ProductDetailsResource(
            productId = this.productid,
            sku = this.sku,
            skuManufacturer = this.skumanufacturer,
            descriptionShort = this.descriptionshort,
            description = this.description,
            descriptionHtml = this.descriptionhtml,
            deliveryStatus = this.deliverystatus,
            moneyPrice = this.moneyprice,
            moneyPriceOrg = this.moneypriceorg,
            moneyPriceIn = this.moneypricein,
            unitLabel = this.unitlabel,
            allowDecimals = this.allowdecimals,
            deliveryInfo = this.deliveryinfo,
            externalInputTitle = this.externalinputtitle,
            offerIsEnabled = this.offerisenabled,
            moneyOfferPrice = this.moneyofferprice,
            offerTitle = this.offertitle,
            offerDateStart = this.offerdatestart ?: 0,
            offerDateEnd = this.offerdateend ?: 0,
            active = this.active,
            activePos = this.activepos,
            vatId = this.vatid,
            deliveryClassId = this.deliveryclassid,
            defaultCategoryId = this.defaultcategoryid ?: 0,
            categories = this.categories,
            manufacturerId = this.manufacturerid ?: "",
            manufacturerUrl = this.manufacturerurl,
            custom1 = this.custom1,
            custom2 = this.custom2,
            custom3 = this.custom3,
            custom4 = this.custom4,
            custom5 = this.custom5,
            stockCountEnable = this.stockcountenable,
            stockAllowBackOrder = this.stockallowbackorder,
            variantParentId = this.variantparentid ?: 0,
            barcode = this.barcode ?: "",
            barcodeAliases = this.barcodealiases,
            similar = this.similar,
            related = this.related,
            accessories = this.accessories,
            offerIsActive = this.offerisactive,
            moneyFinalPrice = this.moneyfinalprice,
            vatValue = this.vatvalue ?: 0,
            productGroupType = this.productgrouptype ?: 0,
            priceListHasVolume = this.pricelisthasvolume,
            variant = this.variant ?: emptyList(),
            customAttributes = this.customattributes ?: "",
            friendly = this.friendly,
            seoTitle = this.seoTitle,
            seoKeywords = this.seoKeywords,
            seoDescription = this.seoDescription,
            title = this.title,
            dateCreated = this.datecreated,
            dateModified = this.datemodified,
            imgUrl = imgUrl ?: ""

        )
    }
}