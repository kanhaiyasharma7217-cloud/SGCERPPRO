package com.shreeganpati.sgcerppro

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileOutputStream

class CatalogPageExporter(
    private val context: Context
) {

    fun exportPages(pdfFile: File): Boolean {

        return try {

            val outputDir = File(
                context.getExternalFilesDir(null),
                "RenderedPages"
            )

            if (!outputDir.exists()) {
                outputDir.mkdirs()
            }

            val descriptor = ParcelFileDescriptor.open(
                pdfFile,
                ParcelFileDescriptor.MODE_READ_ONLY
            )

            val renderer = PdfRenderer(descriptor)

            for (pageIndex in 0 until renderer.pageCount) {

                val page = renderer.openPage(pageIndex)

                val bitmap = Bitmap.createBitmap(
                    page.width * 2,
                    page.height * 2,
                    Bitmap.Config.ARGB_8888
                )

                bitmap.eraseColor(Color.WHITE)

                page.render(
                    bitmap,
                    null,
                    null,
                    PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                )

                val file = File(
                    outputDir,
                    "page_${pageIndex + 1}.jpg"
                )

                FileOutputStream(file).use {

                    bitmap.compress(
                        Bitmap.CompressFormat.JPEG,
                        100,
                        it
                    )

                }

                bitmap.recycle()
                page.close()

            }

            renderer.close()
            descriptor.close()

            true

        } catch (e: Exception) {

            e.printStackTrace()

            false

        }

    }

}