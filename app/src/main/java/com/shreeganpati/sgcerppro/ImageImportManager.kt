package com.shreeganpati.sgcerppro

import android.content.Context
import java.io.File

class ImageImportManager(
    private val context: Context
) {

    companion object {
        const val IMAGE_FOLDER = "ProductImages"
    }

    /**
     * ProductImages Folder Create
     */
    fun createImageFolder(): File {

        val folder = File(
            context.getExternalFilesDir(null),
            IMAGE_FOLDER
        )

        if (!folder.exists()) {
            folder.mkdirs()
        }

        return folder
    }

    /**
     * Get Folder
     */
    fun getImageFolder(): File {
        return createImageFolder()
    }

    /**
     * Image Exists
     */
    fun imageExists(imageName: String): Boolean {

        val file = File(
            createImageFolder(),
            imageName
        )

        return file.exists()
    }

    /**
     * Get Image File
     */
    fun getImageFile(imageName: String): File {

        return File(
            createImageFolder(),
            imageName
        )
    }

}