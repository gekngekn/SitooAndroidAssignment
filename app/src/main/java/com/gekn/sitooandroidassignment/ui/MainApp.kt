package com.gekn.sitooandroidassignment.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gekn.sitooandroidassignment.navigation.NavDest
import com.gekn.sitooandroidassignment.ui.screens.productdetails.ProductDetailsScreen
import com.gekn.sitooandroidassignment.ui.screens.products.ProductsScreen

@Composable
fun MainApp(
) {

    // Navigation
    val navController: NavHostController = rememberNavController()

    Surface {
        NavHostView(
            navController = navController
        )
    }

}

/**
 * Navigate to a route
 */
fun navigate(navController: NavHostController, route: String) {
    navController.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
    }
}

@Composable
fun NavHostView(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavDest.Products.route
    ) {
        composable(
            route = NavDest.Products.route
        ) {
            ProductsScreen(
                navigateToDetails = { id ->
                    navigate(navController, "${NavDest.ProductDetails.route}/$id")
                }
            )
        }
        composable(
            route = "${NavDest.ProductDetails.route}/{ITEM_ID}",
            enterTransition = {
                slideIn(
                    animationSpec = tween(300, easing = LinearEasing)
                ) { fullSize ->
                    IntOffset(fullSize.width, 0)
                } + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOut(
                    animationSpec = tween(300, easing = LinearEasing)
                ) { fullSize ->
                    IntOffset(0, fullSize.height)
                } + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            val itemId = it.arguments?.getString("ITEM_ID")?.toIntOrNull()
            ProductDetailsScreen(
                productId = itemId,
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }

}