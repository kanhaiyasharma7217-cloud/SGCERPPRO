package com.shreeganpati.sgcerppro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    navController: NavController
) {

    val db = remember {
        CustomerDatabase(navController.context)
    }

    var orderList by remember {
        mutableStateOf(db.getAllOrders())
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Orders")

                }

            )

        },

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate("order")

                }

            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )

            }

        }

    ) { padding ->

        if (orderList.isEmpty()) {

            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentAlignment = Alignment.Center

            ) {

                Text("No Orders Found")

            }

        } else {

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)

            ) {

                items(orderList) { order ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {

                                // Future
                                // Order Details

                            }

                    ) {

                        Column(

                            modifier = Modifier
                                .padding(12.dp)

                        ) {

                            Text(
                                text = order.orderNo,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(order.customerName)

                            Spacer(modifier = Modifier.height(5.dp))

                            Text("₹ ${order.grandTotal}")

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(order.orderStatus)

                        }

                    }

                }

            }

        }

    }

}