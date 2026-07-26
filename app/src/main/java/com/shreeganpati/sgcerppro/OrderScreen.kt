package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController
) {
    val db = remember {
        CustomerDatabase(navController.context)
    }

    val cartItems = remember {
        mutableStateListOf<Cart>().apply {
            addAll(db.getCartItems())
        }
    }

    val grandTotal = remember(cartItems) {
        cartItems.sumOf { it.amount }
    }
    var showCustomerDialog by remember {

        mutableStateOf(false)

    }

    var selectedCustomer by remember {

        mutableStateOf<Customer?>(null)

    }
    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("New Order")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Customer",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            // Customer Selection
                        }
                    ) {

                        Text(

                            selectedCustomer?.customerName

                                ?: "Select Customer"

                        )

                    }

                }

            }

            OutlinedCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Products",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {

                            navController.navigate("cart")

                        }
                    ) {

                        Text("Add Products")

                    }

                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    // Save Order

                }

            ) {

                Text("SAVE ORDER")

            }
            if (showCustomerDialog) {

                CustomerSelectionDialog(

                    db = db,

                    onDismiss = {

                        showCustomerDialog = false

                    },

                    onCustomerSelected = {

                        selectedCustomer = it

                        showCustomerDialog = false

                    }

                )

            }
        }

    }


}