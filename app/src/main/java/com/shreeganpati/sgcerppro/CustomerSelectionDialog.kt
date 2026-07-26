package com.shreeganpati.sgcerppro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomerSelectionDialog(

    db: CustomerDatabase,

    onDismiss: () -> Unit,

    onCustomerSelected: (Customer) -> Unit

) {

    var search by remember { mutableStateOf("") }

    val customerList = remember {

        db.getAllCustomers()

    }

    val filteredList = customerList.filter {

        it.customerName.contains(search, true) ||
                it.mobile.contains(search, true)

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        confirmButton = {},

        title = {

            Text("Select Customer")

        },

        text = {

            Column {

                OutlinedTextField(

                    value = search,

                    onValueChange = {

                        search = it

                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {

                        Text("Search")

                    }

                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(

                    modifier = Modifier.height(350.dp)

                ) {

                    items(filteredList) { customer ->

                        Card(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {

                                    onCustomerSelected(customer)

                                }

                        ) {

                            Column(

                                modifier = Modifier.padding(12.dp)

                            ) {

                                Text(customer.customerName)

                                Text(customer.mobile)

                            }

                        }

                    }

                }

            }

        }

    )

}