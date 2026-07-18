package com.shreeganpati.sgcerppro

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

class ExcelPickerState {

    var fileUri by mutableStateOf<Uri?>(null)

}

@Composable
fun rememberExcelPickerState(): ExcelPickerState {

    return remember {
        ExcelPickerState()
    }

}

@Composable
fun rememberExcelLauncher(
    state: ExcelPickerState
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument()
) { uri ->

    state.fileUri = uri

}