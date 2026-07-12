package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ProductListScreen(navController: NavHostController) {

    val context = LocalContext.current

    val database = remember {
        CustomerDatabase(context)
    }

    var search by remember {
        mutableStateOf("")
    }

    var selectedCompany by remember {
        mutableStateOf("All")
    }

    val companyList = remember {
        mutableStateListOf("All").apply {

            database.getAllCompanies().forEach {

                add(it.companyName)

            }

        }
    }

    val productList = remember {

        mutableStateListOf<Product>().apply {

            addAll(database.getAllProducts())

        }

    }

    val filteredProducts = productList.filter {

        val searchMatch =
            it.productName.contains(search, true) ||
                    it.productCode.contains(search, true)

        val companyMatch =
            selectedCompany == "All" ||
                    it.company == selectedCompany

        searchMatch && companyMatch

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

        Spacer(modifier = Modifier.height(15.dp))

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

        Spacer(modifier = Modifier.height(10.dp))

        var expanded by remember {
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = selectedCompany,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Company")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                companyList.forEach {

                    DropdownMenuItem(

                        text = {

                            Text(it)

                        },

                        onClick = {

                            selectedCompany = it

                            expanded = false

                        }

                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(

            text = "Total Products : ${filteredProducts.size}",

            style = MaterialTheme.typography.titleMedium

        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(filteredProducts) { product ->

                ProductCard(

                    product = product,

                    onAddClick = {

                        val success =
                            database.insertCart(

                                productId = product.id,

                                productName = product.productName,

                                company = product.company,

                                dealerRate = product.dealerRate,

                                quantity = 1,

                                amount = product.dealerRate

                            )

                        Toast.makeText(

                            context,

                            if (success)
                                "Added To Cart"
                            else
                                "Failed",

                            Toast.LENGTH_SHORT

                        ).show()

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