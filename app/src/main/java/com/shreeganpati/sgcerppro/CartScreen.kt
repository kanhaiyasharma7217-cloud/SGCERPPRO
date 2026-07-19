package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext

@Composable
fun CartScreen(navController: NavHostController) {

    val context = LocalContext.current

    val database = remember {
        CustomerDatabase(context)
    }
    val cartItems = remember {
        mutableStateListOf<Cart>().apply {
            addAll(database.getCartItems())
        }
    }

    val grandTotal by remember {
        derivedStateOf {
            cartItems.sumOf { it.amount }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Cart is Empty",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(cartItems) { item ->

                                        Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(
                                text = item.productName,
                                fontWeight = FontWeight.Bold
                            )


                            Text("Company : ${item.company}")
                            Text("Dealer Rate : ₹${item.dealerRate}")
                            Text("Qty : ${item.quantity}")
                            Text("Amount : ₹${item.amount}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {

                                    if (database.deleteCartItem(item.id)) {

                                        cartItems.removeAll {
                                            it.id == item.id
                                        }

                                    }

                                }
                            ) {
                                Text("🗑 Remove")
                            }
                        }

                    }

                }

            }

        }

        Text(
            text = "Grand Total : ₹$grandTotal",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                // WhatsApp Order अगले Step में

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Place Order")
        }
        Spacer(modifier = Modifier.height(10.dp))

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