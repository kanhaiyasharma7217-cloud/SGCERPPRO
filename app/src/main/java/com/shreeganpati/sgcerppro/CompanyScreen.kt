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

@Composable
fun CompanyScreen(navController: NavHostController) {

    val context = LocalContext.current
    val database = CustomerDatabase(context)

    var companyCode by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }
    var gst by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Active") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Company Master",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = companyCode,
            onValueChange = { companyCode = it },
            label = { Text("Company Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = discount,
            onValueChange = { discount = it },
            label = { Text("Discount %") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = gst,
            onValueChange = { gst = it },
            label = { Text("GST Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = website,
            onValueChange = { website = it },
            label = { Text("Website") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                val result = database.insertCompany(
                    companyCode = companyCode,
                    companyName = companyName,
                    discount = discount.toDoubleOrNull() ?: 0.0,
                    gst = gst,
                    phone = phone,
                    email = email,
                    address = address,
                    website = website,
                    status = status
                )

                if (result) {

                    Toast.makeText(
                        context,
                        "Company Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    companyCode = ""
                    companyName = ""
                    discount = ""
                    gst = ""
                    phone = ""
                    email = ""
                    address = ""
                    website = ""
                    status = "Active"

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
