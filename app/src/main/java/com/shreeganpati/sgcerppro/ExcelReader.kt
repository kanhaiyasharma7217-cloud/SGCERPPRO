package com.shreeganpati.sgcerppro

import android.content.Context
import android.net.Uri
import org.apache.poi.ss.usermodel.WorkbookFactory

class ExcelReader(
    private val context: Context
) {

    data class ProductExcelRow(
        val company: String,
        val category: String,
        val productName: String,
        val productCode: String,
        val hsn: String,
        val gst: String,
        val mrp: String,
        val stock: String,
        val newArrival: Boolean
    )

    fun readProducts(
        fileUri: Uri
    ): MutableList<ProductExcelRow> {

        val productList = mutableListOf<ProductExcelRow>()

        val inputStream =
            context.contentResolver.openInputStream(fileUri)
                ?: return productList

        val workbook = WorkbookFactory.create(inputStream)

        val sheet = workbook.getSheetAt(0)

        for (i in 1..sheet.lastRowNum) {

            val row = sheet.getRow(i) ?: continue

            productList.add(

                ProductExcelRow(

                    company = row.getCell(0)?.toString() ?: "",

                    category = row.getCell(1)?.toString() ?: "",

                    productName = row.getCell(2)?.toString() ?: "",

                    productCode = row.getCell(3)?.toString() ?: "",

                    hsn = row.getCell(4)?.toString() ?: "",

                    gst = row.getCell(5)?.toString() ?: "",

                    mrp = row.getCell(6)?.toString() ?: "",

                    stock = row.getCell(7)?.toString() ?: "",

                    newArrival =
                        row.getCell(8)?.toString()
                            ?.equals("YES", true) == true

                )

            )

        }

        workbook.close()
        inputStream.close()

        return productList

    }

}