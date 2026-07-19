package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val db = remember { CustomerDatabase(context) }

    var products by remember {
        mutableStateOf(db.getAllProducts())
    }

    var search by remember {
        mutableStateOf("")
    }

    var companyFilter by remember {
        mutableStateOf("All")
    }

    val filteredProducts = products.filter {

        (companyFilter == "All" || it.company == companyFilter) &&
                (
                        it.productName.contains(search, true) ||
                                it.productCode.contains(search, true)
                        )

    }

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(
                onClick = {

                    navController.navigate("product")

                }
            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Product"
                )

            }

        }

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = {
                    Text("Search Product")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        companyFilter = "All"
                    }
                ) {
                    Text("All")
                }

                Button(
                    onClick = {
                        companyFilter = "PEXPO"
                    }
                ) {
                    Text("PEXPO")
                }

                Button(
                    onClick = {
                        companyFilter = "HIC"
                    }
                ) {
                    Text("HIC")
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Total Products : ${filteredProducts.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    db.deleteAllProducts()
                    products = db.getAllProducts()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {

                Icon(
                    Icons.Default.DeleteSweep,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Delete All")

            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {

                items(filteredProducts) { product ->                    ProductCard(
                    product = product,

                    onAddClick = { qty ->

                        CartManager.addToCart(
                            product = product,
                            quantity = qty
                        )

                    },

                    onEditClick = {

                        navController.navigate(
                            "product/${product.id}"
                        )

                    },

                    onDeleteClick = {

                        db.deleteProduct(product.id)
                        products = db.getAllProducts()

                    }

                )

                }

            }

        }

    }

}