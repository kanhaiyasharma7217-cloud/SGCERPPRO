package com.shreeganpati.sgcerppro

data class RegisterRequest(

    val name: String,

    val mobile: String,

    val role: String,

    val firmName: String,

    val gstNo: String,

    val address: String,

    val city: String,

    val state: String,

    val pincode: String,

    val userId: String,

    val password: String

)