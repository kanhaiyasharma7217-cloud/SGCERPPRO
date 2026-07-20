package com.shreeganpati.sgcerppro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

@Composable
fun DashboardScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    val database = remember {
        CustomerDatabase(context)
    }

      val newArrivalProducts = remember {
        database.getNewArrivalProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {

        Text(
            text = "SHREE GANPATI CORPORATION",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0)
        )

        Text(
            text = "Dealer ERP",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))

        /*
if (newArrivalProducts.isNotEmpty()) {

    Text(
        text = "🔥 NEW ARRIVAL",
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(10.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(newArrivalProducts.take(5)) { product ->

            ProductCard(
                product = product,
                onAddClick = {}
            )

        }

    }

    Spacer(modifier = Modifier.height(20.dp))

}
*/
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Customer",
                icon = Icons.Default.People
            ) {
                navController.navigate("customer")
            }

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Product",
                icon = Icons.Default.Inventory
            ) {
                navController.navigate("product")
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Customer List",
                icon = Icons.Default.People
            ) {
                navController.navigate("customerlist")
            }

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Product List",
                icon = Icons.Default.Inventory
            ) {
                navController.navigate("productlist")
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Company",
                icon = Icons.Default.Inventory
            ) {
                navController.navigate("companylist")
            }

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Category",
                icon = Icons.Default.Inventory
            ) {
                navController.navigate("categorylist")
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Reports",
                icon = Icons.Default.Receipt
            ) {
                // Coming Soon
            }

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Settings",
                icon = Icons.Default.Settings
            ) {
                // Coming Soon
            }

        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Reports",
                icon = Icons.Default.Receipt
            ) {
                // Coming Soon
            }

            DashboardCard(
                modifier = Modifier.weight(1f),
                title = "Settings",
                icon = Icons.Default.Settings
            ) {
                // Coming Soon
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Version 1.0",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

    }
}

@Composable
fun DashboardCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .height(130.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1565C0),
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

    }

}