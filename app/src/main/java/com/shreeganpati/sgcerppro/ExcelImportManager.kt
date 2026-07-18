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
                    stock = item.stock,
                    imageUrl = item.imageUrl,
                    isNewArrival = item.newArrival

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