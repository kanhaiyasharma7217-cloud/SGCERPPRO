package com.shreeganpati.sgcerppro

data class OrderItem(

    val id: Int = 0,

    val orderId: Int = 0,

    val productId: Int = 0,

    val productName: String = "",

    val productCode: String = "",

    val quantity: Int = 1,

    val rate: Double = 0.0,

    val gst: Double = 0.0,

    val discount: Double = 0.0,

    val amount: Double = 0.0

)