package com.shreeganpati.sgcerppro

data class Customer(

    var id: Int = 0,

    var customerName: String = "",

    var mobile: String = "",

    var alternateMobile: String = "",

    var gst: String = "",

    var email: String = "",

    var address: String = "",

    var city: String = "",

    var state: String = "",

    var pincode: String = "",

    var openingBalance: Double = 0.0,

    var creditLimit: Double = 0.0

)
