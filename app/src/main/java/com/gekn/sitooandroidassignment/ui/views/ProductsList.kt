@file:OptIn(
    ExperimentalMaterial3Api::class
)

package com.gekn.sitooandroidassignment.ui.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gekn.sitooandroidassignment.R
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.ConnectionState
import com.gekn.sitooandroidassignment.network.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ProductsList(
    modifier: Modifier,
    productsPagingItems: LazyPagingItems<ProductResource>,
    onItemClicked: (id: Int) -> Unit = {}
) {

    val refreshState = rememberPullToRefreshState()

    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            productsPagingItems.refresh()
            refreshState.endRefresh()
        }
    }

    // This will cause re-composition on every network state change
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    Box(
        Modifier.nestedScroll(refreshState.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag(stringResource(id = R.string.test_tag_products_list)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // Show the connectivity status as header if there is no internet connection
            stickyHeader {
                ConnectivityStatus(isConnected)
            }
            items(
                count = productsPagingItems.itemCount,
                itemContent = { item ->
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable { onItemClicked(productsPagingItems[item]!!.id) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        ProductCard(product = productsPagingItems[item]!!)
                    }
                }
            )

            productsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = productsPagingItems.loadState.refresh as LoadState.Error
                        Log.e("ProductsList", "LoadState.Error: ${error.error}")
                        item {
                            EmptyList(
                                onRetry = { productsPagingItems.refresh() }
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingView() }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = productsPagingItems.loadState.append as LoadState.Error
                        Log.e("ProductsList", "LoadState.Error: ${error.error}")
                        item {
                            EmptyList(
                                onRetry = { productsPagingItems.refresh() }
                            )
                        }
                    }
                }
            }

        }

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (60).dp),
            state = refreshState
        )
    }

}

@Preview
@Composable
fun ProductsListPreview() {
    ProductsList(
        modifier = Modifier,
        productsPagingItems = flowOf(
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
        ).collectAsLazyPagingItems()
    )

}