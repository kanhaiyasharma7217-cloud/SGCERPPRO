package com.shreeganpati.sgcerppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("dashboard") {
                        DashboardScreen(navController)
                    }

                    composable("customer") {
                        CustomerScreen(navController)
                    }

                    composable("customerlist") {
                        CustomerListScreen(navController)
                    }

                    composable("product") {
                        ProductScreen(navController)
                    }

                    composable("product/{id}") { backStackEntry ->

                        val productId =
                            backStackEntry.arguments
                                ?.getString("id")
                                ?.toIntOrNull() ?: 0

                        ProductScreen(
                            navController = navController,
                            productId = productId
                        )
                    }

                    composable("productlist") {
                        ProductListScreen(navController)
                    }

                    composable("company") {
                        CompanyScreen(navController)
                    }

                    composable("companylist") {
                        CompanyListScreen(navController)
                    }

                    composable("cart") {
                        CartScreen(navController)
                    }

                    composable("import") {
                        ImportScreen(navController)
                    }

                    composable("importproducts") {
                        ImportProductsScreen(navController)
                    }

                    composable("importimages") {
                        ImportImagesScreen(navController)
                    }

                }
            }
        }
    }
}