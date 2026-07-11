package com.shreeganpati.sgcerppro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCard(
    product: Product,
    onAddClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = product.productName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("Company : ${product.company}")
            Text("Code : ${product.productCode}")
            Text("MRP : ₹${product.mrp}")

            Text(
                text = "Dealer Rate : ₹${product.dealerRate}",
                color = MaterialTheme.colorScheme.primary
            )

            Text("Stock : ${product.stock}")

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onAddClick
            ) {
                Text("Add To Cart")
            }
        }
    }
}