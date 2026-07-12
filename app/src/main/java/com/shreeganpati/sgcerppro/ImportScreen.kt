package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ImportScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            text = "Import Center",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                Toast.makeText(
                    context,
                    "Product Import Coming Soon",
                    Toast.LENGTH_SHORT
                ).show()

            }

        ) {

            Icon(
                Icons.Default.Upload,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Import Products")

        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                Toast.makeText(
                    context,
                    "Customer Import Coming Soon",
                    Toast.LENGTH_SHORT
                ).show()

            }

        ) {

            Icon(
                Icons.Default.Upload,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Import Customers")

        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                Toast.makeText(
                    context,
                    "Company Import Coming Soon",
                    Toast.LENGTH_SHORT
                ).show()

            }

        ) {

            Icon(
                Icons.Default.Upload,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Import Companies")

        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                navController.popBackStack()

            }

        ) {

            Text("Back")

        }

    }

}