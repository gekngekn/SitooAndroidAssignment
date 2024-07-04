package com.gekn.sitooandroidassignment

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.gekn.sitooandroidassignment.ui.screens.productdetails.ProductDetailsScreen
import com.gekn.sitooandroidassignment.ui.theme.SitooAndroidAssignmentTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductDetailsScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testProductsScreen() {
        composeTestRule.activity.setContent {
            SitooAndroidAssignmentTheme {
                ProductDetailsScreen(productId = 1) {

                }
            }
        }

        // Check that the screen is displayed
        val tagScreenName = composeTestRule.activity.getString(R.string.test_tag_product_details_screen)
        composeTestRule.onNodeWithTag(tagScreenName).assertExists()

        val productIdValidator = "1"
        composeTestRule.onNodeWithText(productIdValidator)

    }

}