package com.shreeganpati.sgcerppro

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val database = remember {
        CustomerDatabase(context)
    }

    var companyId by remember {
        mutableStateOf(0)
    }

    var isEditMode by remember {
        mutableStateOf(false)
    }

    var companyCode by remember {
        mutableStateOf("")
    }

    var companyName by remember {
        mutableStateOf("")
    }

    var discount by remember {
        mutableStateOf("")
    }

    var gst by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    var website by remember {
        mutableStateOf("")
    }

    var status by remember {
        mutableStateOf("Active")
    }

    var logoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var logoPath by remember {
        mutableStateOf("")
    }

    val logoPicker =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            logoUri = uri
        }

    var companyList by remember {
        mutableStateOf(database.getAllCompanies())
    }

    var search by remember {
        mutableStateOf("")
    }

    val filteredCompanies =
        companyList.filter {
            it.companyName.contains(search, true) ||
                    it.companyCode.contains(search, true)


        }

    LaunchedEffect(Unit) {

        SelectedCompany.company?.let { company ->

            companyId = company.id
            isEditMode = true

            companyCode = company.companyCode
            companyName = company.companyName
            discount = company.discount.toString()

            gst = company.gst
            phone = company.phone
            email = company.email

            address = company.address
            website = company.website

            logoPath = company.logo
            status = company.status

        }

    }
    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        if (isEditMode)
                            "Edit Company"
                        else
                            "Company Master"
                    )

                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            SelectedCompany.company = null
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null
                        )
                    }

                }

            )

        }

    ) { padding ->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)

        ) {

            OutlinedTextField(

                value = search,

                onValueChange = {
                    search = it
                },

                label = {
                    Text("Search Company")
                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(

                    modifier = Modifier.padding(12.dp),

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    when {

                        logoUri != null -> {

                            AsyncImage(

                                model = logoUri,

                                contentDescription = null,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),

                                contentScale = ContentScale.Crop

                            )

                        }

                        logoPath.isNotEmpty() -> {

                            AsyncImage(

                                model = logoPath,

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

                        onClick = {

                            logoPicker.launch("image/*")

                        }

                    ) {

                        Icon(
                            Icons.Default.Photo,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Upload Logo")

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(

                value = companyCode,

                onValueChange = {
                    companyCode = it
                },

                label = {
                    Text("Company Code")
                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = companyName,

                onValueChange = {
                    companyName = it
                },

                label = {
                    Text("Company Name")
                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = discount,

                onValueChange = {
                    discount = it
                },

                label = {
                    Text("Dealer Discount %")
                },

                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = gst,
                onValueChange = { gst = it },
                label = { Text("GST Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = website,
                onValueChange = { website = it },
                label = { Text("Website") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = status,
                onValueChange = { status = it },
                label = { Text("Status") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    if (companyName.isBlank() || companyCode.isBlank()) {

                        Toast.makeText(
                            context,
                            "Enter Company Name & Company Code",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@Button
                    }

                    val logo = if (logoUri != null)
                        ImageStorage.saveImage(
                            context,
                            logoUri!!
                        )
                    else
                        logoPath

                    val result =
                        if (isEditMode) {

                            database.updateCompany(

                                id = companyId,

                                companyCode = companyCode,

                                companyName = companyName,

                                discount = discount.toDoubleOrNull() ?: 0.0,

                                gst = gst,

                                phone = phone,

                                email = email,

                                address = address,

                                website = website,

                                logo = logo,

                                status = status

                            )

                        } else {

                            database.insertCompany(

                                companyCode = companyCode,

                                companyName = companyName,

                                discount = discount.toDoubleOrNull() ?: 0.0,

                                gst = gst,

                                phone = phone,

                                email = email,

                                address = address,

                                website = website,

                                logo = logo,

                                status = status

                            )

                        }
                    if (result) {

                        Toast.makeText(
                            context,
                            if (isEditMode)
                                "Company Updated Successfully"
                            else
                                "Company Saved Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        SelectedCompany.company = null

                        companyCode = ""
                        companyName = ""
                        discount = ""
                        gst = ""
                        phone = ""
                        email = ""
                        address = ""
                        website = ""
                        status = "Active"

                        logoUri = null
                        logoPath = ""

                        companyId = 0
                        isEditMode = false

                        companyList = database.getAllCompanies()

                    }

                }

            ) {

                Icon(
                    Icons.Default.Save,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    if (isEditMode)
                        "UPDATE COMPANY"
                    else
                        "SAVE COMPANY",
                    fontSize = 18.sp
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Company List",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            filteredCompanies.forEach { company ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = company.companyName,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Code : ${company.companyCode}")
                        Text("Discount : ${company.discount}%")
                        Text("GST : ${company.gst}")

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            OutlinedButton(

                                onClick = {

                                    companyId = company.id
                                    companyCode = company.companyCode
                                    companyName = company.companyName
                                    discount = company.discount.toString()
                                    gst = company.gst
                                    phone = company.phone
                                    email = company.email
                                    address = company.address
                                    website = company.website
                                    logoPath = company.logo
                                    status = company.status

                                    isEditMode = true

                                }

                            ) {

                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text("Edit")

                            }

                            OutlinedButton(

                                onClick = {

                                    database.deleteCompany(company.id)

                                    companyList =
                                        database.getAllCompanies()

                                }

                            ) {

                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text("Delete")

                            }

                        }

                    }

                }

            }

        }

    }

}