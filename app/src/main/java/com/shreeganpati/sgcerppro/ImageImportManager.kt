package com.shreeganpati.sgcerppro

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.FileOutputStream

class ImageImportManager(
    private val context: Context
) {

    companion object {
        const val IMAGE_FOLDER = "ProductImages"
    }

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

    fun getImageFolder(): File {
        return createImageFolder()
    }

    fun imageExists(imageName: String): Boolean {

        return File(
            createImageFolder(),
            imageName
        ).exists()

    }

    fun getImageFile(imageName: String): File {

        return File(
            createImageFolder(),
            imageName
        )

    }

    fun importImages(folderUri: Uri): Triple<Int, Int, Int> {

        val folder = DocumentFile.fromTreeUri(context, folderUri)
            ?: return Triple(0, 0, 0)

        val destinationFolder = createImageFolder()

        var imported = 0
        var skipped = 0
        var failed = 0

        folder.listFiles().forEach { file ->

            if (!file.isFile) return@forEach

            val name = file.name ?: return@forEach

            if (
                !name.endsWith(".jpg", true) &&
                !name.endsWith(".jpeg", true) &&
                !name.endsWith(".png", true)
            ) {
                return@forEach
            }

            val destination = File(destinationFolder, name)

            if (destination.exists()) {
                skipped++
                return@forEach
            }

            try {

                context.contentResolver
                    .openInputStream(file.uri)
                    ?.use { input ->

                        FileOutputStream(destination).use { output ->
                            input.copyTo(output)
                        }

                    }

                imported++

            } catch (e: Exception) {

                failed++

            }

        }

        return Triple(
            imported,
            skipped,
            failed
        )

    }

}