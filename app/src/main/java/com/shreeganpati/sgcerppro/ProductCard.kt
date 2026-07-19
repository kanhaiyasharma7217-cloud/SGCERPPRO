package com.shreeganpati.sgcerppro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.io.File

@Composable
fun ProductCard(
    product: Product,
    onAddClick: (Int) -> Unit,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {

    val context = LocalContext.current

    var qty by remember {
        mutableStateOf(1)
    }

    val imageFile = File(
        context.getExternalFilesDir(null),
        "ProductImages/${product.image1}"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Row(
                verticalAlignment = Alignment.Top
            ) {

                if (
                    product.image1.isNotEmpty() &&
                    imageFile.exists()
                ) {

                    AsyncImage(
                        model = imageFile,
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

                    Text("Code : ${product.productCode}")
                    Text("Company : ${product.company}")
                    Text("Category : ${product.category}")

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "MRP : ₹${product.mrp}",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Dealer : ₹${product.dealerRate}",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    val mrp = product.mrp.toDoubleOrNull() ?: 0.0
                    val dealer = product.dealerRate.toDoubleOrNull() ?: 0.0

                    if (mrp > 0 && dealer > 0) {

                        val discount =
                            ((mrp - dealer) / mrp) * 100

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Discount : ${discount.toInt()}%",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Stock : ${product.stock}"
                    )

                    if (product.stock.toIntOrNull() == 0) {

                        Text(
                            text = "Out Of Stock",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Qty",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedButton(
                    onClick = {
                        if (qty > 1) qty--
                    }
                ) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = qty.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = {
                        qty++
                    }
                ) {
                    Text("+")
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }

                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    onAddClick(qty)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {

                Text(
                    text = "ADD TO CART"
                )

            }

        }

    }

}