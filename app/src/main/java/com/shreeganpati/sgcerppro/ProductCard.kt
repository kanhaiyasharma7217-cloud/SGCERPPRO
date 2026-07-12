package com.shreeganpati.sgcerppro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProductCard(
    product: Product,
    onAddClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            if (product.imageUrl.isNotEmpty()) {

                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

            } else {

                Image(
                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = product.productName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text("Company : ${product.company}")

                Text("Category : ${product.category}")

                Text("Code : ${product.productCode}")

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "MRP : ₹${product.mrp}",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Dealer : ₹${product.dealerRate}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                val mrpValue = product.mrp.toDoubleOrNull() ?: 0.0

                val dealerValue = product.dealerRate.toDoubleOrNull() ?: 0.0

                val saveAmount = mrpValue - dealerValue

                val discount =
                    if (mrpValue > 0)
                        (saveAmount * 100 / mrpValue).toInt()
                    else
                        0

                Spacer(modifier = Modifier.height(6.dp))

                if (product.isNewArrival) {

                    AssistChip(
                        onClick = { },
                        label = {
                            Text("🆕 NEW")
                        }
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                }

                AssistChip(
                    onClick = { },
                    label = {
                        Text("🟢 $discount% OFF")
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "💰 You Save ₹${String.format("%.0f", saveAmount)}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
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

}