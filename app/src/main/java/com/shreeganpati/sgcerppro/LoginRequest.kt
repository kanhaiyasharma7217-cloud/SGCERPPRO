package com.shreeganpati.sgcerppro

data class LoginRequest(

    val mobile: String = "",

    val otp: String = "",

    val userId: String = "",

    val password: String = ""

)