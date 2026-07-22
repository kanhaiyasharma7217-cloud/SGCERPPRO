package com.shreeganpati.sgcerppro

import android.content.Context
import android.net.Uri
import java.io.File

class PdfImportManager(
    private val context: Context
) {

    private val pdfFolder by lazy {

        File(
            context.filesDir,
            "Catalog"
        ).apply {

            if (!exists()) {
                mkdirs()
            }

        }

    }

    fun importPdf(
        uri: Uri
    ): File? {

        return try {

            val outputFile = File(
                pdfFolder,
                "pexpo_catalog.pdf"
            )

            context.contentResolver.openInputStream(uri)?.use { input ->

                outputFile.outputStream().use { output ->

                    input.copyTo(output)

                }

            }

            outputFile

        } catch (e: Exception) {

            e.printStackTrace()

            null

        }

    }

    fun getCatalogFile(): File {

        return File(
            pdfFolder,
            "pexpo_catalog.pdf"
        )

    }

    fun isCatalogImported(): Boolean {

        return getCatalogFile().exists()

    }

    fun deleteCatalog(): Boolean {

        return getCatalogFile().delete()

    }

}