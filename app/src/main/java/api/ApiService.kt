package com.shreeganpati.sgcerppro.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(ApiRoutes.REQUEST_OTP)
    suspend fun requestOtp(
        @Body body: Map<String, String>
    ): Response<Any>

    @POST(ApiRoutes.LOGIN)
    suspend fun verifyOtp(
        @Body body: Map<String, String>
    ): Response<Any>

    @GET(ApiRoutes.CUSTOMERS)
    suspend fun getCustomers(): Response<Any>

    @GET(ApiRoutes.PRODUCTS)
    suspend fun getProducts(): Response<Any>

    @GET(ApiRoutes.ORDERS)
    suspend fun getOrders(): Response<Any>

}