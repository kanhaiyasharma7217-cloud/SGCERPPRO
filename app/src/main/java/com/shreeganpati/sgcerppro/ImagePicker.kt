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
fun rememberGalleryLauncher(
    state: ImagePickerState
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
) { uri ->

    state.imageUri = uri

}