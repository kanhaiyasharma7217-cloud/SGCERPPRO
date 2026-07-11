package com.shreeganpati.sgcerppro

data class Cart(

    val id: Int = 0,

    val productId: Int,

    val productName: String,

    val company: String,

    val mrp: Double,

    val dealerRate: Double,

    var quantity: Int,

    val imageUrl: String,

    val amount: Double

)