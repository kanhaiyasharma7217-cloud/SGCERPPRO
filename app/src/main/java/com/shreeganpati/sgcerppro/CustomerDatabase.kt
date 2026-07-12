package com.shreeganpati.sgcerppro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomerDatabase(context: Context) :
    SQLiteOpenHelper(context, "SGC_ERP.db", null, 6) {

    override fun onCreate(db: SQLiteDatabase) {


        // Customers Table
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

        // Products Table
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

        // Cart Table
        db.execSQL(
            """
            CREATE TABLE Cart(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                productId INTEGER,
                productName TEXT,
                company TEXT,
                dealerRate TEXT,
                quantity INTEGER,
                amount TEXT
            )
            """.trimIndent()
        )

        // Companies Table
        db.execSQL(
            """
            CREATE TABLE Companies(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               companyCode TEXT,
               companyName TEXT,
               discount REAL,
               gst TEXT,
               phone TEXT,
               email TEXT,
               address TEXT,
               website TEXT,
               status TEXT
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
        db.execSQL("DROP TABLE IF EXISTS Cart")
        db.execSQL("DROP TABLE IF EXISTS Companies")

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
                        imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("imageUrl")),
                        isNewArrival = false
                    )
                    )



            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return productList
    }

    fun insertCart(
        productId: Int,
        productName: String,
        company: String,
        dealerRate: String,
        quantity: Int,
        amount: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("productId", productId)
        values.put("productName", productName)
        values.put("company", company)
        values.put("dealerRate", dealerRate)
        values.put("quantity", quantity)
        values.put("amount", amount)

        val result = db.insert("Cart", null, values)

        db.close()

        return result != -1L
    }

    fun getCartItems(): MutableList<Cart> {

        val cartList = mutableListOf<Cart>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Cart",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                cartList.add(

                    Cart(
                        id = cursor.getInt(0),
                        productId = cursor.getInt(1),
                        productName = cursor.getString(2),
                        company = cursor.getString(3),
                        mrp = 0.0,
                        dealerRate = cursor.getString(4).toDoubleOrNull() ?: 0.0,
                        quantity = cursor.getInt(5),
                        imageUrl = "",
                        amount = cursor.getString(6).toDoubleOrNull() ?: 0.0
                    )

                )

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return cartList
    }

    fun deleteCartItem(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Cart",
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun insertCompany(
        companyName: String,
        discount: Double,
        gst: String,
        phone: String,
        email: String,
        address: String,
        website: String,
        status: String,
        companyCode: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("companyName", companyName)
        values.put("discount", discount)
        values.put("gst", gst)
        values.put("phone", phone)
        values.put("email", email)
        values.put("address", address)
        values.put("website", website)
        values.put("status", status)
        values.put("companyCode", companyCode)

        val result = db.insert("Companies", null, values)


        db.close()

        return result != -1L
    }

    fun getAllCompanies(): MutableList<Company> {

        val companyList = mutableListOf<Company>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Companies ORDER BY companyName",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                companyList.add(
                    Company(
                        id = cursor.getInt(0),
                        companyCode = cursor.getString(1),
                        companyName = cursor.getString(2),
                        discount = cursor.getDouble(3),
                        gst = cursor.getString(4),
                        phone = cursor.getString(5),
                        email = cursor.getString(6),
                        address = cursor.getString(7),
                        website = cursor.getString(8),
                        status = cursor.getString(9)
                    )
                )

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return companyList
    }

    fun deleteCompany(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Companies",
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
   }
}

