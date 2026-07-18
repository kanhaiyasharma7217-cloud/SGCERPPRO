package com.shreeganpati.sgcerppro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ImportProductsScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val pickerState = rememberExcelPickerState()

    val launcher = rememberExcelLauncher(
        pickerState
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Product Import",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {

                launcher.launch(
                    arrayOf(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                    )
                )

            }
        ) {

            Text("Choose Excel File")

        }

        Spacer(modifier = Modifier.height(20.dp))

        if (pickerState.fileUri != null) {

            Text(
                text = "Selected File"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = pickerState.fileUri.toString()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    val importManager = ExcelImportManager(context)

                    val result = importManager.importProducts(
                        pickerState.fileUri!!
                    )

                    Toast.makeText(
                        context,
                        """
Imported : ${result.imported}
Skipped : ${result.skipped}
Failed : ${result.failed}
    """.trimIndent(),
                        Toast.LENGTH_LONG
                    ).show()

                }
            ) {

                Text("IMPORT")

            }

        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            }
        ) {

            Text("Back")

        }

    }

}