package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

        Spacer(modifier = Modifier.height(25.dp))

        ImportButton(
            title = "Import Products",
            icon = Icons.Default.Inventory
        ) {
            navController.navigate("importproducts")}

        Spacer(modifier = Modifier.height(12.dp))

        ImportButton(
            title = "Import Customers",
            icon = Icons.Default.People
        ) {
            navController.navigate("importproducts")        }

        Spacer(modifier = Modifier.height(12.dp))

        ImportButton(
            title = "Import Companies",
            icon = Icons.Default.Business
        ) {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(12.dp))

        ImportButton(
            title = "Import Product Images",
            icon = Icons.Default.Image
        ) {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(12.dp))

        ImportButton(
            title = "Backup Database",
            icon = Icons.Default.Backup
        ) {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(12.dp))

        ImportButton(
            title = "Restore Database",
            icon = Icons.Default.Restore
        ) {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(20.dp))

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

@Composable
fun ImportButton(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(title)
    }
}