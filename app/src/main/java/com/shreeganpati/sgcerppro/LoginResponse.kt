package com.shreeganpati.sgcerppro

data class LoginResponse(

    val success: Boolean,

    val message: String,

    val token: String,

    val user: User

)