package com.shreeganpati.sgcerppro

data class Company(

    val id: Int = 0,
    val companyCode: String,
    val companyName: String,
    val discount: Double,
    val gst: String,
    val phone: String,
    val email: String,
    val address: String,
    val website: String,
    val status: String

)