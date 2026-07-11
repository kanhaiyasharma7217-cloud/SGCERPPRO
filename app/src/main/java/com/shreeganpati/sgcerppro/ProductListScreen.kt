package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProductListScreen(navController: NavHostController) {

    val context = LocalContext.current
    val database = CustomerDatabase(context)

    val productList = remember {
        database.getAllProducts()
    }

    var search by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Product List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            label = {
                Text("Search Product")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(
                productList.filter {
                    it.productName.contains(search, ignoreCase = true)
                }
            ) { product ->

                ProductCard(
                    product = product,
                    onAddClick = {

                    }
                )

            }

        }


        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}