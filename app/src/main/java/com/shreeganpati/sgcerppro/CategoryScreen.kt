package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val db = remember { CustomerDatabase(context) }

    var categoryId by remember { mutableStateOf(0) }
    var isEdit by remember { mutableStateOf(false) }

    var categoryCode by remember { mutableStateOf("") }
    var categoryName by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Active") }

    var search by remember { mutableStateOf("") }

    var categoryList by remember {
        mutableStateOf(db.getAllCategories())
    }

    val companyList = remember {
        db.getAllCompanies()
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val filteredList = categoryList.filter {

        it.categoryName.contains(search, true) ||
                it.categoryCode.contains(search, true)

    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        if (isEdit)
                            "Edit Category"
                        else
                            "Category Master"
                    )

                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null
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
                .padding(16.dp)

        ) {

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                },
                label = {
                    Text("Search Category")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = categoryCode,
                onValueChange = {
                    categoryCode = it
                },
                label = {
                    Text("Category Code")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = categoryName,
                onValueChange = {
                    categoryName = it
                },
                label = {
                    Text("Category Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(

                expanded = expanded,

                onExpandedChange = {

                    expanded = !expanded

                }

            ) {

                OutlinedTextField(

                    value = company,

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

                                Text(it.companyName)

                            },

                            onClick = {

                                company = it.companyName
                                expanded = false

                            }

                        )

                    }

                }

            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                },
                label = {
                    Text("Description")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = status,
                onValueChange = {
                    status = it
                },
                label = {
                    Text("Status")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    if (categoryCode.isBlank() || categoryName.isBlank()) {

                        Toast.makeText(
                            context,
                            "Enter Category Code & Category Name",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@Button
                    }

                    val result = if (isEdit) {

                        db.updateCategory(

                            id = categoryId,

                            categoryCode = categoryCode,

                            categoryName = categoryName,

                            company = company,

                            description = description,

                            status = status

                        )

                    } else {

                        db.insertCategory(

                            categoryCode = categoryCode,

                            categoryName = categoryName,

                            company = company,

                            description = description,

                            status = status

                        )

                    }

                    if (result) {

                        Toast.makeText(
                            context,
                            if (isEdit)
                                "Category Updated Successfully"
                            else
                                "Category Saved Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        categoryId = 0
                        isEdit = false

                        categoryCode = ""
                        categoryName = ""
                        company = ""
                        description = ""
                        status = "Active"

                        categoryList = db.getAllCategories()

                    }

                }

            ) {

                Icon(
                    Icons.Default.Save,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(

                    if (isEdit)
                        "UPDATE CATEGORY"
                    else
                        "SAVE CATEGORY"

                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Category List",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(filteredList) { category ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            Text(
                                text = category.categoryName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text("Code : ${category.categoryCode}")
                            Text("Company : ${category.company}")
                            Text("Status : ${category.status}")

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {

                                OutlinedButton(
                                    onClick = {

                                        categoryId = category.id
                                        categoryCode = category.categoryCode
                                        categoryName = category.categoryName
                                        company = category.company
                                        description = category.description
                                        status = category.status

                                        isEdit = true

                                    }
                                ) {

                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = null
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text("Edit")

                                }

                                OutlinedButton(
                                    onClick = {

                                        db.deleteCategory(category.id)

                                        categoryList = db.getAllCategories()

                                    }
                                ) {

                                    Icon(
                                        Icons.Default.Delete,
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

    }

}