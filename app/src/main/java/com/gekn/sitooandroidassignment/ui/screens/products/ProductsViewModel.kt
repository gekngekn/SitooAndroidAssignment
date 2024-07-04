package com.gekn.sitooandroidassignment.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gekn.sitooandroidassignment.domain.repositories.ProductsPagingPagingRepositoryImpl
import com.gekn.sitooandroidassignment.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    repository: ProductsPagingPagingRepositoryImpl
) : ViewModel() {

    private val _uiProductsState = MutableStateFlow(
        ProductsScreenState(
            appState = AppState.Default,
            products = repository.getProductsPaging().cachedIn(viewModelScope)
        )
    )

    val uiProductsState: StateFlow<ProductsScreenState> = _uiProductsState.asStateFlow()

}