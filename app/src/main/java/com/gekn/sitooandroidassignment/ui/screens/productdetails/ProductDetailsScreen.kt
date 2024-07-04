package com.gekn.sitooandroidassignment.ui.screens.productdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gekn.sitooandroidassignment.R
import com.gekn.sitooandroidassignment.domain.models.ProductDetailsResource
import com.gekn.sitooandroidassignment.ui.AppState
import com.gekn.sitooandroidassignment.ui.views.ErrorView
import com.gekn.sitooandroidassignment.ui.views.LoadingView
import com.gekn.sitooandroidassignment.ui.views.ProductDetailsCard
import com.gekn.sitooandroidassignment.ui.views.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productId: Int?,
    onClose: () -> Unit
) {
    val uiState by viewModel.uiProductDetailsState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .testTag(stringResource(id = R.string.test_tag_product_details_screen)),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                canNavigate = true,
                navigateUp = {
                    onClose()
                }
            )
        }

    ) { innerPadding ->

        if (productId == null) {
            ErrorView(
                modifier = Modifier
                    .padding(innerPadding),
                icon = R.drawable.ic_sad,
                message = stringResource(id = R.string.error_invalid_product_id)
            )
        } else {

            // Fetch products from API on first composition
            LaunchedEffect(Unit) {
                uiState.getProductDetails(productId)
            }

            when (uiState.appState) {
                is AppState.Default -> {}
                is AppState.Loading -> {
                    LoadingView()
                }
                is AppState.Content -> {
                    uiState.product?.let {
                        ProductDetailsContent(
                            modifier = Modifier
                                .padding(innerPadding),
                            it
                        )
                    }
                }
                is AppState.Error -> {
                    ErrorView(
                        modifier = Modifier.padding(innerPadding),
                        icon = R.drawable.ic_sad,
                        message = stringResource(id = R.string.error_no_content_found)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDetailsContent(
    modifier: Modifier,
    product: ProductDetailsResource
) {

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.imgUrl)
                .crossfade(true)
                .error(R.drawable.ic_image_placeholder)
                .build(),
            contentDescription = "Product image",
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.TopCenter
        )
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(280.dp))

            ProductDetailsCard(
                product = product
            )
        }
    }

}

@Preview
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsContent(
        modifier = Modifier,
        product = ProductDetailsResource(
            productId = 1,
            sku = "sku",
            skuManufacturer = "skuman",
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
            categories = emptyList(),
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
            barcodeAliases = emptyList(),
            similar = emptyList(),
            related = listOf(0),
            accessories = listOf(0, 0),
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