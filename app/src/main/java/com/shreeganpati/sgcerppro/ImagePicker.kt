package com.shreeganpati.sgcerppro

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

class ImagePickerState {

    var imageUri by mutableStateOf<Uri?>(null)

}

@Composable
fun rememberImagePickerState(): ImagePickerState {

    return remember {

        ImagePickerState()

    }

}

@Composable
fun rememberFolderLauncher(
    onFolderSelected: (Uri) -> Unit
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocumentTree()
) { uri ->

    uri?.let {
        onFolderSelected(it)
    }

}
