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
fun CustomerScreen(navController: NavHostController) {

    var customerName by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var alternateMobile by remember { mutableStateOf("") }
    var gst by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var openingBalance by remember { mutableStateOf("") }
    var creditLimit by remember { mutableStateOf("") }

    val scroll = rememberScrollState()
    val context = LocalContext.current
    val database = CustomerDatabase(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {

        Text(
            text = "Customer Master",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = alternateMobile,
            onValueChange = { alternateMobile = it },
            label = { Text("Alternate Mobile") },
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
            value = city,
            onValueChange = { city = it },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state,
            onValueChange = { state = it },
            label = { Text("State") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pincode,
            onValueChange = { pincode = it },
            label = { Text("Pincode") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = openingBalance,
            onValueChange = { openingBalance = it },
            label = { Text("Opening Balance") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = creditLimit,
            onValueChange = { creditLimit = it },
            label = { Text("Credit Limit") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {

                val result = database.insertCustomer(
                    customerName,
                    mobile,
                    alternateMobile,
                    gst,
                    email,
                    address,
                    city,
                    state,
                    pincode,
                    openingBalance,
                    creditLimit
                )

                if (result) {

                    Toast.makeText(
                        context,
                        "Customer Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    customerName = ""
                    mobile = ""
                    alternateMobile = ""
                    gst = ""
                    email = ""
                    address = ""
                    city = ""
                    state = ""
                    pincode = ""
                    openingBalance = ""
                    creditLimit = ""

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

        Spacer(modifier = Modifier.height(20.dp))
    }
}