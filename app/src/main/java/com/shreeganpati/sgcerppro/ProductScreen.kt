package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController) {

    val context = LocalContext.current
    val database = remember { CustomerDatabase(context) }

    var productName by remember { mutableStateOf("") }
    var productCode by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var hsnCode by remember { mutableStateOf("") }
    var gstRate by remember { mutableStateOf("") }
    var purchaseRate by remember { mutableStateOf("") }
    var saleRate by remember { mutableStateOf("") }
    var mrp by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    val companyList = remember {
        database.getAllCompanies()
    }

    var expandedCompany by remember {
        mutableStateOf(false)
    }

    val filteredCompanies = companyList.filter {

        it.companyName.contains(
            company,
            ignoreCase = true
        )

    }

    val imageUrl = ""

    val dealerRate = remember(
        company,
        mrp,
        companyList
    ) {

        val mrpValue = mrp.toDoubleOrNull() ?: 0.0

        val selectedCompany = companyList.find {

            it.companyName.equals(
                company,
                ignoreCase = true
            )

        }

        if (selectedCompany != null) {

            val rate =
                mrpValue -
                        (mrpValue * selectedCompany.discount / 100)

            String.format("%.2f", rate)

        } else {

            ""

        }

    }

    val scroll = rememberScrollState()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp)

    ) {

        Text(

            text = "Product Master",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(
            expanded = expandedCompany,
            onExpandedChange = {
                expandedCompany = !expandedCompany
            }
        ) {

            OutlinedTextField(
                value = company,
                onValueChange = {
                    company = it
                    expandedCompany = true
                },
                label = {
                    Text("Company")
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expandedCompany)
                }
            )

            ExposedDropdownMenu(
                expanded = expandedCompany,
                onDismissRequest = {
                    expandedCompany = false
                }
            ) {

                filteredCompanies.forEach { item ->

                    DropdownMenuItem(
                        text = {
                            Text(item.companyName)
                        },
                        onClick = {

                            company = item.companyName

                            expandedCompany = false

                        }
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dealerRate,
            onValueChange = {},
            readOnly = true,
            label = { Text("Dealer Rate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = category,
            onValueChange = {
                category = it
            },
            label = {
                Text("Category")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = {
                productName = it
            },
            label = {
                Text("Product Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = productCode,
            onValueChange = {
                productCode = it
            },
            label = {
                Text("Product Code")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = hsnCode,
            onValueChange = {
                hsnCode = it
            },
            label = {
                Text("HSN Code")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = gstRate,
            onValueChange = {
                gstRate = it
            },
            label = {
                Text("GST %")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = purchaseRate,
            onValueChange = {
                purchaseRate = it
            },
            label = {
                Text("Purchase Rate")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = saleRate,
            onValueChange = {
                saleRate = it
            },
            label = {
                Text("Sale Rate")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = mrp,
            onValueChange = {
                mrp = it
            },
            label = {
                Text("MRP")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = stock,
            onValueChange = {
                stock = it
            },
            label = {
                Text("Opening Stock")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                val result = database.insertProduct(
                    productName = productName,
                    productCode = productCode,
                    category = category,
                    company = company,
                    hsnCode = hsnCode,
                    gstRate = gstRate,
                    purchaseRate = purchaseRate,
                    saleRate = saleRate,
                    mrp = mrp,
                    dealerRate = dealerRate,
                    stock = stock,
                    imageUrl = imageUrl
                )

                if (result) {

                    Toast.makeText(
                        context,
                        "Product Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    productName = ""
                    productCode = ""
                    category = ""
                    company = ""
                    hsnCode = ""
                    gstRate = ""
                    purchaseRate = ""
                    saleRate = ""
                    mrp = ""
                    stock = ""

                    expandedCompany = false

                } else {

                    Toast.makeText(
                        context,
                        "Save Failed",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "SAVE",
                fontSize = 18.sp
            )

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
