package com.shreeganpati.sgcerppro

data class Customer(

    val id: Int = 0,

    val customerName: String,

    val mobile: String,

    val alternateMobile: String,

    val gst: String,

    val email: String,

    val address: String,

    val city: String,

    val state: String,

    val pincode: String,

    val openingBalance: Double,

    val creditLimit: Double

)