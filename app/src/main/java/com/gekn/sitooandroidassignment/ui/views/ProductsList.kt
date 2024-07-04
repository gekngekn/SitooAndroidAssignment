@file:OptIn(
    ExperimentalMaterial3Api::class
)

package com.gekn.sitooandroidassignment.ui.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ProductsList(
    modifier: Modifier,
    productsPagingItems: LazyPagingItems<ProductResource>,
    onItemClicked: (id: Int) -> Unit = {}
) {

    // This will cause re-composition on every network state change
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // Pull to refresh state
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val refreshState = remember {
        object : PullToRefreshState {
            private val anim = Animatable(0f, Float.VectorConverter)

            override val distanceFraction
                get() = anim.value

            override suspend fun animateToThreshold() {
                anim.animateTo(1f, spring(dampingRatio = Spring.DampingRatioHighBouncy))
            }

            override suspend fun animateToHidden() {
                anim.animateTo(0f)
            }

            override suspend fun snapTo(targetValue: Float) {
                anim.snapTo(targetValue)
            }
        }
    }

    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            productsPagingItems.refresh()
            delay(1000L) // Without the delay the refresh indicator stays visible after refresh
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        modifier = modifier,
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier
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
                    ProductCard(
                        product = productsPagingItems[item]!!,
                        onClick = { onItemClicked(productsPagingItems[item]!!.id) }
                    )
                }
            )

            productsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = productsPagingItems.loadState.refresh as LoadState.Error
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
                        item {
                            EmptyList(
                                onRetry = { productsPagingItems.refresh() }
                            )
                        }
                    }
                }
            }

        }

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
        ).collectAsLazyPagingItems(),
        onItemClicked = {}
    )

}