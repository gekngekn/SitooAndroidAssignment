package com.gekn.sitooandroidassignment.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gekn.sitooandroidassignment.domain.PrimitiveExtensions.secondsToDateString
import com.gekn.sitooandroidassignment.domain.models.ProductDetailsResource

@Composable
fun ProductDetailsCard(
    product: ProductDetailsResource
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp
        ),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            ProductDetailItem(
                field = ProductDetailsResource::productId.name,
                value = product.productId
            )
            ProductDetailItem(field = ProductDetailsResource::sku.name, value = product.sku)
            ProductDetailItem(
                field = ProductDetailsResource::skuManufacturer.name,
                value = product.skuManufacturer
            )
            ProductDetailItem(
                field = ProductDetailsResource::descriptionShort.name,
                value = product.descriptionShort
            )
            ProductDetailItem(
                field = ProductDetailsResource::description.name,
                value = product.description
            )
            ProductDetailItem(
                field = ProductDetailsResource::descriptionHtml.name,
                value = product.descriptionHtml
            )
            ProductDetailItem(
                field = ProductDetailsResource::deliveryStatus.name,
                value = product.deliveryStatus
            )
            ProductDetailItem(
                field = ProductDetailsResource::moneyPrice.name,
                value = product.moneyPrice
            )
            ProductDetailItem(
                field = ProductDetailsResource::unitLabel.name,
                value = product.unitLabel
            )
            ProductDetailItem(
                field = ProductDetailsResource::allowDecimals.name,
                value = product.allowDecimals
            )
            ProductDetailItem(
                field = ProductDetailsResource::deliveryInfo.name,
                value = product.deliveryInfo
            )
            ProductDetailItem(
                field = ProductDetailsResource::externalInputTitle.name,
                value = product.externalInputTitle
            )
            ProductDetailItem(
                field = ProductDetailsResource::offerIsEnabled.name,
                value = product.offerIsEnabled
            )
            ProductDetailItem(
                field = ProductDetailsResource::moneyOfferPrice.name,
                value = product.moneyOfferPrice
            )
            ProductDetailItem(
                field = ProductDetailsResource::offerTitle.name,
                value = product.offerTitle
            )
            ProductDetailItem(
                field = ProductDetailsResource::offerDateStart.name,
                value = if (product.offerDateStart.toInt() == 0) 0 else product.offerDateStart.secondsToDateString()
            )
            ProductDetailItem(
                field = ProductDetailsResource::offerDateEnd.name,
                value = if (product.offerDateEnd.toInt() == 0) 0 else product.offerDateEnd.secondsToDateString()
            )
            ProductDetailItem(field = ProductDetailsResource::active.name, value = product.active)
            ProductDetailItem(
                field = ProductDetailsResource::activePos.name,
                value = product.activePos
            )
            ProductDetailItem(field = ProductDetailsResource::vatId.name, value = product.vatId)
            ProductDetailItem(
                field = ProductDetailsResource::deliveryClassId.name,
                value = product.deliveryClassId
            )
            ProductDetailItem(
                field = ProductDetailsResource::defaultCategoryId.name,
                value = product.defaultCategoryId
            )
            ProductDetailItem(
                field = ProductDetailsResource::categories.name,
                value = product.categories
            )
            ProductDetailItem(
                field = ProductDetailsResource::manufacturerId.name,
                value = product.manufacturerId
            )
            ProductDetailItem(
                field = ProductDetailsResource::manufacturerUrl.name,
                value = product.manufacturerUrl
            )
            ProductDetailItem(field = ProductDetailsResource::custom1.name, value = product.custom1)
            ProductDetailItem(field = ProductDetailsResource::custom2.name, value = product.custom2)
            ProductDetailItem(field = ProductDetailsResource::custom3.name, value = product.custom3)
            ProductDetailItem(field = ProductDetailsResource::custom4.name, value = product.custom4)
            ProductDetailItem(field = ProductDetailsResource::custom5.name, value = product.custom5)
            ProductDetailItem(
                field = ProductDetailsResource::stockCountEnable.name,
                value = product.stockCountEnable
            )
            ProductDetailItem(
                field = ProductDetailsResource::variantParentId.name,
                value = product.variantParentId
            )
            ProductDetailItem(field = ProductDetailsResource::barcode.name, value = product.barcode)
            ProductDetailItem(
                field = ProductDetailsResource::barcodeAliases.name,
                value = product.barcodeAliases
            )
            ProductDetailItem(field = ProductDetailsResource::similar.name, value = product.similar)
            ProductDetailItem(field = ProductDetailsResource::related.name, value = product.related)
            ProductDetailItem(
                field = ProductDetailsResource::accessories.name,
                value = product.accessories
            )
            ProductDetailItem(
                field = ProductDetailsResource::offerIsActive.name,
                value = product.offerIsActive
            )
            ProductDetailItem(
                field = ProductDetailsResource::moneyFinalPrice.name,
                value = product.moneyFinalPrice
            )
            ProductDetailItem(
                field = ProductDetailsResource::vatValue.name,
                value = product.vatValue
            )
            ProductDetailItem(
                field = ProductDetailsResource::productGroupType.name,
                value = product.productGroupType
            )
            ProductDetailItem(
                field = ProductDetailsResource::priceListHasVolume.name,
                value = product.priceListHasVolume
            )
            ProductDetailItem(field = ProductDetailsResource::variant.name, value = product.variant)
            ProductDetailItem(
                field = ProductDetailsResource::customAttributes.name,
                value = product.customAttributes
            )
            ProductDetailItem(
                field = ProductDetailsResource::friendly.name,
                value = product.friendly
            )
            ProductDetailItem(
                field = ProductDetailsResource::seoTitle.name,
                value = product.seoKeywords
            )
            ProductDetailItem(
                field = ProductDetailsResource::seoDescription.name,
                value = product.seoDescription
            )
            ProductDetailItem(field = ProductDetailsResource::title.name, value = product.title)
            ProductDetailItem(
                field = ProductDetailsResource::dateCreated.name,
                value = product.dateCreated.secondsToDateString()
            )
            ProductDetailItem(
                field = ProductDetailsResource::dateModified.name,
                value = product.dateModified.secondsToDateString()
            )

        }
    }
}

@Preview
@Composable
fun ProductDetailsCardPreview() {
    ProductDetailsCard(
        product = ProductDetailsResource(
            productId = 1,
            sku = "sku",
            skuManufacturer = "",
            descriptionShort = "desc",
            description = "desc",
            descriptionHtml = "desc",
            deliveryStatus = "delstatus",
            moneyPrice = "1515.00",
            moneyPriceOrg = "150.00",
            moneyPriceIn = "1511.00",
            unitLabel = "label",
            allowDecimals = true,
            deliveryInfo = "info",
            externalInputTitle = "inputtitle",
            offerIsEnabled = true,
            moneyOfferPrice = "651.00",
            offerTitle = "title",
            offerDateStart = 0,
            offerDateEnd = 0,
            active = true,
            activePos = true,
            vatId = 12,
            deliveryClassId = 11,
            defaultCategoryId = 666,
            categories = listOf(21, 12, 666, 4355),
            manufacturerId = "id",
            manufacturerUrl = "url",
            custom1 = "custom1",
            custom2 = "custom2",
            custom3 = "custom3",
            custom4 = "custom4",
            custom5 = "custom5",
            stockCountEnable = true,
            stockAllowBackOrder = true,
            variantParentId = 0,
            barcode = "",
            barcodeAliases = listOf("Barcode", "Lorem Ipsum"),
            similar = listOf(12, 66, 45, 444),
            related = listOf(0, 1, 2, 3),
            accessories = listOf(10, 10),
            offerIsActive = true,
            moneyFinalPrice = "2121",
            vatValue = 2,
            productGroupType = 1,
            priceListHasVolume = false,
            variant = listOf(""),
            customAttributes = "",
            friendly = "12",
            seoTitle = "11",
            seoKeywords = "666",
            seoDescription = "",
            title = "",
            dateCreated = 1567423374,
            dateModified = 1567433483,
            imgUrl = ""
        )
    )

}