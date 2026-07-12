package com.shreeganpati.sgcerppro

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object ImageStorage {

    fun saveImage(
        context: Context,
        uri: Uri
    ): String {

        val inputStream =
            context.contentResolver.openInputStream(uri)
                ?: return ""

        val fileName =
            "IMG_${System.currentTimeMillis()}.jpg"

        val file = File(
            context.filesDir,
            fileName
        )

        val outputStream =
            FileOutputStream(file)

        inputStream.copyTo(outputStream)

        inputStream.close()
        outputStream.close()

        return file.absolutePath

    }

}