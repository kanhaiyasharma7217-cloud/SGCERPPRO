package com.shreeganpati.sgcerppro

data class Product(
    val id: Int,
    val productName: String,
    val productCode: String,
    val hsnCode: String,
    val gstRate: String,
    val purchaseRate: String,
    val saleRate: String,
    val mrp: String,
    val dealerRate: String,
    val stock: String,
    val company: String,
    val category: String,
    val imageUrl: String
)