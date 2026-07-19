package com.shreeganpati.sgcerppro

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ImportImagesScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val imageManager = remember { ImageImportManager(context) }

    var folderUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var resultText by remember {
        mutableStateOf("")
    }

    val folderLauncher = rememberFolderLauncher {

        folderUri = it

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "Import Images",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                folderLauncher.launch(null)

            }
        ) {

            Text("Select Image Folder")

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            enabled = folderUri != null,

            onClick = {

                folderUri?.let {

                    val result = imageManager.importImages(it)

                    resultText =
                        "Imported : ${result.first}\n" +
                                "Skipped : ${result.second}\n" +
                                "Failed : ${result.third}"

                    Toast.makeText(
                        context,
                        "Image Import Completed",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

        ) {

            Text("Import Images")

        }

        Spacer(modifier = Modifier.height(20.dp))

        if (folderUri == null) {

            Text("No Folder Selected")

        } else {

            Text("Folder Selected Successfully")

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(resultText)

    }

}