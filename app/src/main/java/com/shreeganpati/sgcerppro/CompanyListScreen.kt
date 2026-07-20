package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext

@Composable
fun CompanyListScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val database = remember {
        CustomerDatabase(context)
    }

    var search by remember {
        mutableStateOf("")
    }

    var companyList by remember {
        mutableStateOf(database.getAllCompanies())
    }

    val filteredList = companyList.filter {

        it.companyName.contains(search, true) ||
                it.companyCode.contains(search, true)

    }

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate("company")

                }

            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Company"
                )

            }

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)

        ) {

            OutlinedTextField(

                value = search,

                onValueChange = {

                    search = it

                },

                modifier = Modifier.fillMaxWidth(),

                leadingIcon = {

                    Icon(
                        Icons.Default.Search,
                        null
                    )

                },

                label = {

                    Text("Search Company")

                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = "Total Companies : ${filteredList.size}",

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {

                items(filteredList) { company ->

                         CompanyItem(

                        company = company,

                             onEdit = {
                                 navController.navigate("company")
                             },

                             onDelete = {

                                 database.deleteCompany(company.id)

                                 companyList = database.getAllCompanies()

                        }

                    )

                }

            }

        }

    }

}
@Composable
fun CompanyItem(
    company: Company,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = company.companyName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Code : ${company.companyCode}")

            Text("GST : ${company.gst}")

            Text("Discount : ${company.discount}%")

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(
                    onClick = onEdit
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )

                }

                IconButton(
                    onClick = onDelete
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )

                }

            }

        }

    }

}