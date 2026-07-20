package com.shreeganpati.sgcerppro

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavHostController,
    productId: Int = 0
) {

    val context = LocalContext.current
    val database = remember { CustomerDatabase(context) }

    //-------------------------
    // Edit Mode
    //-------------------------

    val isEditMode = productId > 0

    //-------------------------
    // States
    //-------------------------

    var productName by remember { mutableStateOf("") }
    var productCode by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }

    var hsnCode by remember { mutableStateOf("") }
    var gstRate by remember { mutableStateOf("") }

    var purchaseRate by remember { mutableStateOf("") }
    var saleRate by remember { mutableStateOf("") }

    var mrp by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    var description by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Existing Image Path
    var imagePath by remember { mutableStateOf("") }

    var isNewArrival by remember { mutableStateOf(false) }

    //-------------------------
    // Companies
    //-------------------------

    var companyList by remember {
        mutableStateOf(listOf<Company>())
    }

    LaunchedEffect(Unit) {
        companyList = database.getAllCompanies()
    }

    var expandedCompany by remember {
        mutableStateOf(false)
    }

    val filteredCompanies =
        if (company.isBlank()) {
            companyList
        } else {
            companyList.filter {
                it.companyName.contains(company, ignoreCase = true)
            }
        }

    //-------------------------
    // Image Picker
    //-------------------------

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    //-------------------------
    // Dealer Rate
    //-------------------------

    val dealerRate = remember(
        company,
        mrp,
        companyList
    ) {

        val mrpValue = mrp.toDoubleOrNull() ?: 0.0

        val selectedCompany =
            companyList.find {
                it.companyName.equals(
                    company,
                    true
                )
            }

        if (selectedCompany != null) {

            val rate =
                mrpValue -
                        (mrpValue * selectedCompany.discount / 100)

            "%.2f".format(rate)

        } else {

            ""

        }

    }

    //-------------------------
    // Load Product For Edit
    //-------------------------

    LaunchedEffect(productId) {

        if (isEditMode) {

            database.getProductById(productId)?.let { product ->

                productName = product.productName
                productCode = product.productCode
                category = product.category
                company = product.company

                hsnCode = product.hsnCode
                gstRate = product.gstRate

                purchaseRate = product.purchaseRate
                saleRate = product.saleRate

                mrp = product.mrp
                stock = product.stock

                description = product.description

                imagePath = product.image1

                isNewArrival = product.isNewArrival
            }

        }

    }

    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {

        Text(
            text = if (isEditMode) "Edit Product" else "Product Master",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                when {

                    imageUri != null -> {

                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                    }

                    imagePath.isNotEmpty() -> {

                        AsyncImage(
                            model = imagePath,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        launcher.launch("image/*")
                    }
                ) {
                    Text("📷 Choose Product Photo")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row {

                    Checkbox(
                        checked = isNewArrival,
                        onCheckedChange = {
                            isNewArrival = it
                        }
                    )

                    Text("New Arrival Product")

                }

            }

        }

        Spacer(modifier = Modifier.height(15.dp))

        ExposedDropdownMenuBox(
            expanded = expandedCompany,
            onExpandedChange = {
                expandedCompany = !expandedCompany
            }
        ) {

            OutlinedTextField(
                value = company,
                onValueChange = {
                    company = it
                    expandedCompany = true
                },
                label = { Text("Company") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expandedCompany)
                }
            )

            ExposedDropdownMenu(
                expanded = expandedCompany,
                onDismissRequest = {
                    expandedCompany = false
                }
            ) {

                filteredCompanies.forEach {

                    DropdownMenuItem(
                        text = {
                            Text(it.companyName)
                        },
                        onClick = {

                            company = it.companyName
                            expandedCompany = false

                        }
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dealerRate,
            onValueChange = {},
            readOnly = true,
            label = { Text("Dealer Rate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = productCode,
            onValueChange = { productCode = it },
            label = { Text("Product Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = hsnCode,
            onValueChange = { hsnCode = it },
            label = { Text("HSN Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = gstRate,
            onValueChange = { gstRate = it },
            label = { Text("GST %") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = purchaseRate,
            onValueChange = { purchaseRate = it },
            label = { Text("Purchase Rate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = saleRate,
            onValueChange = { saleRate = it },
            label = { Text("Sale Rate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = mrp,
            onValueChange = { mrp = it },
            label = { Text("MRP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Opening Stock") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (
                    productName.isBlank() ||
                    productCode.isBlank() ||
                    company.isBlank()
                ) {

                    Toast.makeText(
                        context,
                        "Please fill all required fields",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val finalImage = when {
                    imageUri != null ->
                        ImageStorage.saveImage(context, imageUri!!)

                    else ->
                        imagePath
                }

                val result = if (isEditMode) {

                    database.updateProduct(

                        id = productId,

                        productName = productName,
                        productCode = productCode,

                        category = category,
                        company = company,

                        hsnCode = hsnCode,
                        gstRate = gstRate,

                        purchaseRate = purchaseRate,
                        saleRate = saleRate,

                        mrp = mrp,
                        dealerRate = dealerRate,

                        specialRate = "",
                        specialDiscount = "",

                        stock = stock,

                        description = description,

                        image1 = finalImage,
                        image2 = "",
                        image3 = "",
                        image4 = "",
                        image5 = "",

                        videoUrl = "",
                        pdfUrl = "",

                        offerTitle = "",
                        offerStart = "",
                        offerEnd = "",

                        isNewArrival = isNewArrival,
                        isFeatured = false,
                        isBestSeller = false,
                        isSpecial = false,
                        isFestivalOffer = false,
                        isComingSoon = false,

                        status = "Active"
                    )

                } else {

                    database.insertProduct(

                        productName = productName,
                        productCode = productCode,

                        category = category,
                        company = company,

                        hsnCode = hsnCode,
                        gstRate = gstRate,

                        purchaseRate = purchaseRate,
                        saleRate = saleRate,

                        mrp = mrp,
                        dealerRate = dealerRate,

                        specialRate = "",
                        specialDiscount = "",

                        stock = stock,

                        description = description,

                        image1 = finalImage,
                        image2 = "",
                        image3 = "",
                        image4 = "",
                        image5 = "",

                        videoUrl = "",
                        pdfUrl = "",

                        offerTitle = "",
                        offerStart = "",
                        offerEnd = "",

                        isNewArrival = isNewArrival,
                        isFeatured = false,
                        isBestSeller = false,
                        isSpecial = false,
                        isFestivalOffer = false,
                        isComingSoon = false,

                        status = "Active"
                    )

                }

                if (result) {

                    Toast.makeText(
                        context,
                        if (isEditMode)
                            "Product Updated Successfully"
                        else
                            "Product Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    navController.popBackStack()

                } else {

                    Toast.makeText(
                        context,
                        if (isEditMode)
                            "Update Failed"
                        else
                            "Save Failed",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        ) {

            Text(
                text = if (isEditMode) "UPDATE" else "SAVE",
                fontSize = 18.sp
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

    }
}