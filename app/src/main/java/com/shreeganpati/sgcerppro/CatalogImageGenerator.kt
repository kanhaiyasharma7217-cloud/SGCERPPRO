package com.shreeganpati.sgcerppro

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File

class CatalogImageGenerator(
    private val context: Context
) {

    interface ProgressListener {
        fun onProgress(current: Int, total: Int)
    }

    fun renderAllPages(
        pdfFile: File,
        listener: ProgressListener? = null
    ): List<Bitmap> {

        val pages = mutableListOf<Bitmap>()

        if (!pdfFile.exists()) {
            return pages
        }

        val descriptor = ParcelFileDescriptor.open(
            pdfFile,
            ParcelFileDescriptor.MODE_READ_ONLY
        )

        val renderer = PdfRenderer(descriptor)

        val totalPages = renderer.pageCount

        for (i in 0 until totalPages) {

            val page = renderer.openPage(i)

            val bitmap = Bitmap.createBitmap(
                page.width * 2,
                page.height * 2,
                Bitmap.Config.ARGB_8888
            )

            bitmap.eraseColor(android.graphics.Color.WHITE)

            page.render(
                bitmap,
                null,
                null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
            )

            pages.add(bitmap)

            listener?.onProgress(i + 1, totalPages)

            page.close()
        }

        renderer.close()
        descriptor.close()

        return pages
    }

}