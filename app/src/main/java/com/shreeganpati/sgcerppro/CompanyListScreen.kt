package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CompanyListScreen(navController: NavHostController) {

    val context = LocalContext.current
    val database = remember { CustomerDatabase(context) }

    val companyList = remember {
        mutableStateListOf<Company>().apply {
            addAll(database.getAllCompanies())
       }
    }

    var search by remember { mutableStateOf("") }
    var deleteCompany by remember { mutableStateOf<Company?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Company List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Search Company") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Total Companies : ${companyList.size}"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate("company")
            }
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Add New Company")

        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(

                companyList.filter {

                    it.companyName.contains(search, true) ||
                            it.companyCode.contains(search, true)

                }

            ) { company ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = company.companyName,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Code : ${company.companyCode}")
                        Text("Discount : ${company.discount}%")
                        Text("Status : ${company.status}")

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            OutlinedButton(
                                onClick = {

                                    // Edit Screen (Next Step)

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text("Edit")

                            }

                            Button(
                                onClick = {

                                    deleteCompany = company

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text("Delete")

                            }

                        }

                    }

                }

            }

        }

    }

    deleteCompany?.let { company ->

        AlertDialog(

            onDismissRequest = {

                deleteCompany = null

            },

            title = {

                Text("Delete Company")

            },

            text = {

                Text("Are you sure you want to delete ${company.companyName}?")

            },

            confirmButton = {

                Button(

                    onClick = {

                        if (database.deleteCompany(company.id)) {

                            companyList.remove(company)

                        }

                        deleteCompany = null

                    }

                ) {

                    Text("Delete")

                }

            },

            dismissButton = {

                OutlinedButton(

                    onClick = {

                        deleteCompany = null

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }

}

