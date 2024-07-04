package com.gekn.sitooandroidassignment.ui.screens.products

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gekn.sitooandroidassignment.R
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.ui.views.ProductsList
import com.gekn.sitooandroidassignment.ui.views.TopAppBar
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    navigateToDetails: (id: Int) -> Unit
) {

    val uiProductsState by viewModel.uiProductsState.collectAsStateWithLifecycle()
    val productsPagingSource = uiProductsState.products.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .testTag(stringResource(id = R.string.test_tag_products_screen)),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                canNavigate = false,
                navigateUp = {}
            )
        }
    ) { innerPadding ->

        ProductsContent(
            modifier = Modifier
                .padding(innerPadding),
            productsPagingSource,
            onProductClicked = { id ->
                navigateToDetails(id)
            }
        )
    }
}

@Composable
fun ProductsContent(
    modifier: Modifier,
    products: LazyPagingItems<ProductResource>,
    onProductClicked: (id: Int) -> Unit
) {
    ProductsList(
        modifier = modifier,
        products,
        onItemClicked = onProductClicked
    )
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductsContent(
        modifier = Modifier,
        products = flowOf(
            PagingData.from(
                listOf(
                    ProductResource(1, "Name1", "565.00"),
                    ProductResource(2, "Name2", "1025.00"),
                    ProductResource(3, "Name3", "65.00"),
                    ProductResource(4, "Name4", "14.00"),
                    ProductResource(4, "Name4", "14.00"),
                    ProductResource(4, "Name4", "14.00"),
                    ProductResource(5, "Name5", "125.00")
                ),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        ).collectAsLazyPagingItems(),
        onProductClicked = {}
    )
}