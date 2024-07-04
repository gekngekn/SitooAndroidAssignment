package com.gekn.sitooandroidassignment

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.gekn.sitooandroidassignment.domain.ProductsMapper.toProductsResource
import com.gekn.sitooandroidassignment.domain.models.ProductResource
import com.gekn.sitooandroidassignment.network.models.NetworkProducts
import com.gekn.sitooandroidassignment.ui.screens.products.ProductsScreen
import com.gekn.sitooandroidassignment.ui.theme.SitooAndroidAssignmentTheme
import com.gekn.sitooandroidassignment.utils.readJSONFromAssets
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductsScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            SitooAndroidAssignmentTheme {
                ProductsScreen {

                }
            }
        }
    }

    @Test
    fun testProductsScreenIsDisplayed() {
        // Check that the screen is displayed
        val tagScreenName = composeTestRule.activity.getString(R.string.test_tag_products_screen)
        composeTestRule.onNodeWithTag(tagScreenName).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun displays_products_list() {
        // assert the products list is displayed
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.test_tag_products_list))
            .assertIsDisplayed()

        composeTestRule.waitUntilAtLeastOneExists(hasText(getMockProducts().first().name), 10000)

        getMockProducts().forEach {
            composeTestRule.onNodeWithText(it.name).isDisplayed()
        }

    }

    private fun getMockProducts(): List<ProductResource> {
        val gson = Gson()
        val products = gson.fromJson(readJSONFromAssets(composeTestRule.activity.baseContext, "mockProductsData.json"), NetworkProducts::class.java)
        return products.items.toProductsResource()
    }


}