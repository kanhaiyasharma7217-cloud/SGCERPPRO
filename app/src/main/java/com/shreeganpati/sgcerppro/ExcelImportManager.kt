package com.shreeganpati.sgcerppro

import android.content.Context
import android.net.Uri

class ExcelImportManager(
    private val context: Context
) {

    data class ImportResult(
        val imported: Int,
        val skipped: Int,
        val failed: Int
    )

    fun importProducts(
        fileUri: Uri
    ): ImportResult {

        val database = CustomerDatabase(context)

        val excelReader = ExcelReader(context)

        val productList = excelReader.readProducts(fileUri)

        var imported = 0
        var skipped = 0
        var failed = 0

        for (item in productList) {

            try {

                // Duplicate Product
                if (database.isProductExists(item.productCode)) {
                    skipped++
                    continue
                }
                val success = database.insertProduct(

                    productName = item.productName,
                    productCode = item.productCode,
                    category = item.category,
                    company = item.company,
                    hsnCode = item.hsn,
                    gstRate = item.gst,
                    purchaseRate = item.purchaseRate,
                    saleRate = item.saleRate,
                    mrp = item.mrp,
                    dealerRate = item.dealerRate,
                    specialRate = "",
                    specialDiscount = "",
                    stock = item.stock,
                    description = "",
                    image1 = item.imageUrl,
                    image2 = "",
                    image3 = "",
                    image4 = "",
                    image5 = "",
                    videoUrl = "",
                    pdfUrl = "",
                    offerTitle = "",
                    offerStart = "",
                    offerEnd = "",
                    isNewArrival = item.newArrival,
                    isFeatured = false,
                    isBestSeller = false,
                    isSpecial = false,
                    isFestivalOffer = false,
                    isComingSoon = false,
                    status = "Active"

                )

                if (success) {
                    imported++
                } else {
                    failed++
                }

            } catch (e: Exception) {

                e.printStackTrace()
                failed++

            }

        }

        return ImportResult(
            imported = imported,
            skipped = skipped,
            failed = failed
        )

    }

}
// आगे Database Insert होगा