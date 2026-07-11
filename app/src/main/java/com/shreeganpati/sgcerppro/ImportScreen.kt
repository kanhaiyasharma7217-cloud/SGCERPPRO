package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ImportScreen(navController: NavHostController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "IMPORT PRODUCTS",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                Toast.makeText(
                    context,
                    "Excel Import Coming Soon",
                    Toast.LENGTH_SHORT
                ).show()

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Import Excel",
                fontSize = 18.sp
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

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