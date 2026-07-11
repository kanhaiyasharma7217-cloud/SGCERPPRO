package com.shreeganpati.sgcerppro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomerDatabase(context: Context) :
    SQLiteOpenHelper(context, "SGC_ERP.db", null, 3) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE Customers(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                customerName TEXT,
                mobile TEXT,
                alternateMobile TEXT,
                gst TEXT,
                email TEXT,
                address TEXT,
                city TEXT,
                state TEXT,
                pincode TEXT,
                openingBalance TEXT,
                creditLimit TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
    CREATE TABLE Products(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        productName TEXT,
        productCode TEXT,
        category TEXT,
        company TEXT,
        hsnCode TEXT,
        gstRate TEXT,
        purchaseRate TEXT,
        saleRate TEXT,
        mrp TEXT,
        dealerRate TEXT,
        stock TEXT,
        imageUrl TEXT
    )
    """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS Customers")
        db.execSQL("DROP TABLE IF EXISTS Products")
        onCreate(db)
    }

    fun insertCustomer(
        customerName: String,
        mobile: String,
        alternateMobile: String,
        gst: String,
        email: String,
        address: String,
        city: String,
        state: String,
        pincode: String,
        openingBalance: String,
        creditLimit: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("customerName", customerName)
        values.put("mobile", mobile)
        values.put("alternateMobile", alternateMobile)
        values.put("gst", gst)
        values.put("email", email)
        values.put("address", address)
        values.put("city", city)
        values.put("state", state)
        values.put("pincode", pincode)
        values.put("openingBalance", openingBalance)
        values.put("creditLimit", creditLimit)

        val result = db.insert("Customers", null, values)
        fun getAllCustomers(): MutableList<Customer> {

            val customerList = mutableListOf<Customer>()

            val db = readableDatabase

            val cursor = db.rawQuery(
                "SELECT * FROM Customers ORDER BY customerName",
                null
            )

            if (cursor.moveToFirst()) {

                do {

                    customerList.add(

                        Customer(
                            id = cursor.getInt(0),
                            customerName = cursor.getString(1),
                            mobile = cursor.getString(2),
                            alternateMobile = cursor.getString(3),
                            gst = cursor.getString(4),
                            email = cursor.getString(5),
                            address = cursor.getString(6),
                            city = cursor.getString(7),
                            state = cursor.getString(8),
                            pincode = cursor.getString(9),
                            openingBalance = cursor.getString(10).toDoubleOrNull() ?: 0.0,
                            creditLimit = cursor.getString(11).toDoubleOrNull() ?: 0.0
                        )

                    )

                } while (cursor.moveToNext())
            }

            cursor.close()
            db.close()

            return customerList
        }
        db.close()

        return result != -1L
    }

    fun getAllCustomers(): MutableList<Customer> {

        val customerList = mutableListOf<Customer>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Customers ORDER BY customerName",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                customerList.add(

                    Customer(
                        id = cursor.getInt(0),
                        customerName = cursor.getString(1),
                        mobile = cursor.getString(2),
                        alternateMobile = cursor.getString(3),
                        gst = cursor.getString(4),
                        email = cursor.getString(5),
                        address = cursor.getString(6),
                        city = cursor.getString(7),
                        state = cursor.getString(8),
                        pincode = cursor.getString(9),
                        openingBalance = cursor.getString(10).toDoubleOrNull() ?: 0.0,
                        creditLimit = cursor.getString(11).toDoubleOrNull() ?: 0.0
                    )

                )

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return customerList
    }

    fun insertProduct(
        productName: String,
        productCode: String,
        category: String,
        company: String,
        hsnCode: String,
        gstRate: String,
        purchaseRate: String,
        saleRate: String,
        mrp: String,
        dealerRate: String,
        stock: String,
        imageUrl: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("productName", productName)
        values.put("productCode", productCode)
        values.put("category", category)
        values.put("company", company)
        values.put("hsnCode", hsnCode)
        values.put("gstRate", gstRate)
        values.put("purchaseRate", purchaseRate)
        values.put("saleRate", saleRate)
        values.put("mrp", mrp)
        values.put("dealerRate", dealerRate)
        values.put("stock", stock)
        values.put("imageUrl", imageUrl)

        val result = db.insert("Products", null, values)

        db.close()

        return result != -1L
    }

    fun getAllProducts(): MutableList<Product> {

        val productList = mutableListOf<Product>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Products ORDER BY productName",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                productList.add(

                    Product(

                        id = cursor.getInt(0),
                        productName = cursor.getString(1),
                        productCode = cursor.getString(2),
                        category = cursor.getString(3),
                        company = cursor.getString(4),
                        hsnCode = cursor.getString(5),
                        gstRate = cursor.getString(6),
                        purchaseRate = cursor.getString(7),
                        saleRate = cursor.getString(8),
                        mrp = cursor.getString(9),
                        dealerRate = cursor.getString(10),
                        stock = cursor.getString(11),
                        imageUrl = cursor.getString(12)

                    )

                )

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return productList
    }
}
