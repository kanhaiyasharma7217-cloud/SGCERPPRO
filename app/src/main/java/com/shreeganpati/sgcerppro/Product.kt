package com.shreeganpati.sgcerppro

data class Product(

    val id: Int = 0,

    val productName: String = "",

    val productCode: String = "",

    val category: String = "",

    val company: String = "",

    val hsnCode: String = "",

    val gstRate: String = "",

    val purchaseRate: String = "",

    val saleRate: String = "",

    val mrp: String = "",

    val dealerRate: String = "",

    val specialRate: String = "",

    val specialDiscount: String = "",

    val stock: String = "",

    val description: String = "",

    val image1: String = "",

    val image2: String = "",

    val image3: String = "",

    val image4: String = "",

    val image5: String = "",

    val videoUrl: String = "",

    val pdfUrl: String = "",

    val offerTitle: String = "",

    val offerStart: String = "",

    val offerEnd: String = "",

    val isNewArrival: Boolean = false,

    val isFeatured: Boolean = false,

    val isBestSeller: Boolean = false,

    val isSpecial: Boolean = false,

    val isFestivalOffer: Boolean = false,

    val isComingSoon: Boolean = false,

    val status: String = "Active"

)