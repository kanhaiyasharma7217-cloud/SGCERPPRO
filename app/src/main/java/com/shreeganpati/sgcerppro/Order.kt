package com.shreeganpati.sgcerppro

data class Order(

    val id: Int = 0,

    val orderNo: String = "",

    val customerId: Int = 0,

    val customerName: String = "",

    val mobile: String = "",

    val address: String = "",

    val orderDate: String = "",

    val subTotal: Double = 0.0,

    val discount: Double = 0.0,

    val gst: Double = 0.0,

    val grandTotal: Double = 0.0,

    val paymentMode: String = "Cash",

    val paymentStatus: String = "Pending",

    val orderStatus: String = "Pending"

)