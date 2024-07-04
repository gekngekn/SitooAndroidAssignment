package com.gekn.sitooandroidassignment.ui.screens.productdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductDetailsResource
import com.gekn.sitooandroidassignment.domain.repositories.ProductDetailsRepositoryImpl
import com.gekn.sitooandroidassignment.network.NetworkResult
import com.gekn.sitooandroidassignment.ui.AppState
import com.gekn.sitooandroidassignment.utils.exceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductDetailsRepositoryImpl
) : ViewModel() {

    private val _uiProductDetailsState = MutableStateFlow(
        ProductDetailsScreenState(
            appState = AppState.Default,
            product = null,
            getProductDetails = { id ->
                getProductDetails(id)
            }
        )
    )

    val uiProductDetailsState: StateFlow<ProductDetailsScreenState> =
        _uiProductDetailsState.asStateFlow()

    private fun getProductDetails(id: Int) {

        _uiProductDetailsState.update {
            it.copy(
                appState = AppState.Loading
            )
        }

        viewModelScope.launch(exceptionHandler) {

            // Launch all async operations concurrently
            val deferredDetails = async { repository.getProductDetails(id) }
            val deferredSite = async { repository.getSite() }
            val deferredImage = async { repository.getProductImage(id) }

            // Await results and handle errors individually
            val detailsResult = try {
                deferredDetails.await()
            } catch (e: Exception) {
                Log.e("ProductDetailsViewModel", "Error fetching details", e)
                NetworkResult.Error(
                    code = -1,
                    message = "Error fetching details"
                )
            }

            val siteResult = try {
                deferredSite.await()
            } catch (e: Exception) {
                Log.e("ProductDetailsViewModel", "Error fetching site", e)
                NetworkResult.Error(
                    code = -1,
                    message = "Error fetching site information"
                )
            }

            val imageResult = try {
                deferredImage.await()
            } catch (e: Exception) {
                Log.e("ProductDetailsViewModel", "Error fetching image", e)
                NetworkResult.Error(
                    code = -1,
                    message = "Error fetching product image"
                )
            }

            // Build the image URL if both site and image were fetched successfully
            val imgUrl =
                if (siteResult is NetworkResult.Success && imageResult is NetworkResult.Success) {
                    siteResult.data?.siteurl + "res/" + imageResult.data?.resourceid
                } else {
                    null
                }

            // Update UI state based on details result and optional image URL
            _uiProductDetailsState.update {
                when (detailsResult) {
                    is NetworkResult.Success -> {
                        it.copy(
                            appState = AppState.Content,
                            product = detailsResult.data?.toProductDetailsResource(imgUrl)
                        )
                    }

                    is NetworkResult.Error -> {
                        it.copy(
                            appState = AppState.Error(
                                detailsResult.code,
                                detailsResult.message
                            )
                        )
                    }
                }
            }
        }

    }
}