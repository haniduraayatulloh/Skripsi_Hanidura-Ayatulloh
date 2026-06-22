package com.vrasmedia.desainujikeranjang.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrasmedia.desainujikeranjang.R
import com.vrasmedia.desainujikeranjang.ui.screen.*
import com.vrasmedia.desainujikeranjang.ui.screenimport.HomeScreen

object Routes {
    const val Splash = "splash"
    const val Home = "home"
    const val ShoppingList = "shopping_list"
    const val Promo = "promo"
    const val OrderHistory = "order_history"
    const val Profile = "profile"
    const val CategoryDetail = "category_detail"
    const val Cart = "cart"
    const val OrderSummary = "order_summary"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(Routes.Splash) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            })
        }

        composable(Routes.Home) {
            HomeScreen(
                navController = navController,
                onBeverageClick = {
                    navController.navigate(Routes.CategoryDetail)
                },
                onTehBotolClick = {
                    // Diubah agar mengarah ke Cart sesuai permintaan terbaru
                    navController.navigate(Routes.Cart)
                },
                onCartClick = {
                    // Menangani klik ikon keranjang di header
                    navController.navigate(Routes.Cart)
                }
            )
        }

        composable(Routes.ShoppingList) {
            ShoppingListScreen(
                navController = navController,
                // Langsung diarahkan ke Order Summary saat klik + Basket
                onAddToCart = { navController.navigate(Routes.OrderSummary) }
            )
        }

        composable(Routes.Promo) { PromoScreen(navController = navController) }
        composable(Routes.OrderHistory) { OrderHistoryScreen(navController = navController) }
        composable(Routes.Profile) { ProfileScreen(navController = navController) }

        composable(Routes.CategoryDetail) {
            CategoryDetailScreen(
                navController = navController,
                onTehBotolClick = { navController.navigate(Routes.Cart) }
            )
        }

        composable(Routes.Cart) {
            CartScreen(
                onNextClick = { navController.navigate(Routes.OrderSummary) },
                onBackClick = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Home) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.OrderSummary) {
            OrderSummaryScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val actualRoute = navBackStackEntry?.destination?.route ?: currentRoute

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(65.dp)
    ) {
        val navItems = listOf(
            Triple(Routes.Home, R.drawable.ic_nav_home_home, "Home"),
            Triple(Routes.ShoppingList, R.drawable.ic_nav_shopping, "Shopping"),
            Triple(Routes.Promo, R.drawable.ic_nav_promo, "Promo"),
            Triple(Routes.OrderHistory, R.drawable.ic_nav_history, "History"),
            Triple(Routes.Profile, R.drawable.ic_nav_profile, "Profile")
        )

        navItems.forEach { (route, iconRes, label) ->
            val isSelected = actualRoute == route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navigateToBottomNavDestination(navController, route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = label,
                        modifier = Modifier.size(26.dp),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color(0xFFD11F1F) else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

fun navigateToBottomNavDestination(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}