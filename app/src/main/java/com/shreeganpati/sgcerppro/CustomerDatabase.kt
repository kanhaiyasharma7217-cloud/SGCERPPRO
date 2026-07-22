package com.shreeganpati.sgcerppro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomerDatabase(context: Context) :
    SQLiteOpenHelper(
        context,
        "SGC_ERP.db",
        null,
        9
    ) {

    override fun onCreate(db: SQLiteDatabase) {

        //-----------------------------
        // CUSTOMERS TABLE
        //-----------------------------

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
                creditLimit TEXT,
                customerType TEXT,
                whatsapp TEXT,
                birthday TEXT,
                anniversary TEXT,
                notes TEXT,
                status TEXT
            )
            """.trimIndent()
        )

        //-----------------------------
        // PRODUCTS TABLE
        //-----------------------------

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

                specialRate TEXT,
                specialDiscount TEXT,

                stock TEXT,

                description TEXT,

                image1 TEXT,
                image2 TEXT,
                image3 TEXT,
                image4 TEXT,
                image5 TEXT,

                videoUrl TEXT,
                pdfUrl TEXT,

                offerTitle TEXT,
                offerStart TEXT,
                offerEnd TEXT,

                isNewArrival INTEGER DEFAULT 0,
                isFeatured INTEGER DEFAULT 0,
                isBestSeller INTEGER DEFAULT 0,
                isSpecial INTEGER DEFAULT 0,
                isFestivalOffer INTEGER DEFAULT 0,
                isComingSoon INTEGER DEFAULT 0,

                status TEXT
            )
            """.trimIndent()
        )
        //-----------------------------
        // CART TABLE
        //-----------------------------

        db.execSQL(
            """
            CREATE TABLE Cart(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                productId INTEGER,
                productName TEXT,
                company TEXT,
                dealerRate TEXT,
                quantity INTEGER,
                amount TEXT,
                imageUrl TEXT
            )
            """.trimIndent()
        )

        //-----------------------------
        // COMPANIES TABLE
        //-----------------------------

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

    logo TEXT,

    status TEXT
)
            """.trimIndent()
        )

        //-----------------------------
        // OFFERS TABLE
        //-----------------------------

        db.execSQL(
            """
            CREATE TABLE Offers(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                company TEXT,
                productCode TEXT,
                discount TEXT,
                banner TEXT,
                description TEXT,
                startDate TEXT,
                endDate TEXT,
                status TEXT
            )
            """.trimIndent()
        )

        //-----------------------------
        // ANNOUNCEMENTS TABLE
        //-----------------------------

        db.execSQL(
            """
            CREATE TABLE Announcements(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                message TEXT,
                imageUrl TEXT,
                priority TEXT,
                startDate TEXT,
                endDate TEXT
            )
            """.trimIndent()
        )

        //-----------------------------
        // NOTIFICATIONS TABLE
        //-----------------------------

        db.execSQL(
            """
            CREATE TABLE Notifications(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                message TEXT,
                imageUrl TEXT,
                notificationDate TEXT,
                isRead INTEGER DEFAULT 0
            )
            """.trimIndent()
        )

        //-----------------------------
        // MARKETING TABLE
        //-----------------------------

        db.execSQL(
            """
            CREATE TABLE Marketing(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                type TEXT,
                productCode TEXT,
                imageUrl TEXT,
                videoUrl TEXT,
                createdDate TEXT
            )
            """.trimIndent()
        )
        db.execSQL(
            """
    CREATE TABLE Categories(
        id INTEGER PRIMARY KEY AUTOINCREMENT,

        categoryCode TEXT,

        categoryName TEXT,

        company TEXT,

        description TEXT,

        status TEXT
    )
    """.trimIndent()
        )
        db.execSQL("""
    CREATE TABLE OrderItems(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        orderId INTEGER,
        productId INTEGER,
        productName TEXT,
        productCode TEXT,
        quantity INTEGER,
        rate REAL,
        gst REAL,
        discount REAL,
        amount REAL
    )
""".trimIndent())

        db.execSQL("""
    CREATE TABLE Orders(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        orderNo TEXT,
        customerId INTEGER,
        customerName TEXT,
        mobile TEXT,
        address TEXT,
        orderDate TEXT,
        subTotal REAL,
        discount REAL,
        gst REAL,
        grandTotal REAL,
        paymentMode TEXT,
        paymentStatus TEXT,
        orderStatus TEXT
    )
""".trimIndent())
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
        db.execSQL("DROP TABLE IF EXISTS Offers")
        db.execSQL("DROP TABLE IF EXISTS Announcements")
        db.execSQL("DROP TABLE IF EXISTS Notifications")
        db.execSQL("DROP TABLE IF EXISTS Marketing")
        db.execSQL("DROP TABLE IF EXISTS Categories")

        onCreate(db)
    }
    //--------------------------------------------------
    // CUSTOMER FUNCTIONS
    //--------------------------------------------------

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
        creditLimit: String,
        customerType: String,
        whatsapp: String,
        birthday: String,
        anniversary: String,
        notes: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put("customerName", customerName)
            put("mobile", mobile)
            put("alternateMobile", alternateMobile)
            put("gst", gst)
            put("email", email)
            put("address", address)
            put("city", city)
            put("state", state)
            put("pincode", pincode)
            put("openingBalance", openingBalance)
            put("creditLimit", creditLimit)
            put("customerType", customerType)
            put("whatsapp", whatsapp)
            put("birthday", birthday)
            put("anniversary", anniversary)
            put("notes", notes)
            put("status", status)

        }

        val result = db.insert(
            "Customers",
            null,
            values
        )

        db.close()

        return result != -1L
    }

    fun updateCustomer(
        id: Int,
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
        creditLimit: String,
        customerType: String,
        whatsapp: String,
        birthday: String,
        anniversary: String,
        notes: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put("customerName", customerName)
            put("mobile", mobile)
            put("alternateMobile", alternateMobile)
            put("gst", gst)
            put("email", email)
            put("address", address)
            put("city", city)
            put("state", state)
            put("pincode", pincode)
            put("openingBalance", openingBalance)
            put("creditLimit", creditLimit)
            put("customerType", customerType)
            put("whatsapp", whatsapp)
            put("birthday", birthday)
            put("anniversary", anniversary)
            put("notes", notes)
            put("status", status)

        }

        val result = db.update(
            "Customers",
            values,
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun deleteCustomer(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Customers",
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun deleteAllCustomers(): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Customers",
            null,
            null
        )

        db.close()

        return result >= 0
    }

    fun getCustomerById(id: Int): Customer? {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Customers WHERE id=?",
            arrayOf(id.toString())
        )

        var customer: Customer? = null

        if (cursor.moveToFirst()) {

            customer = Customer(

                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                customerName = cursor.getString(cursor.getColumnIndexOrThrow("customerName")),

                mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile")),

                alternateMobile = cursor.getString(cursor.getColumnIndexOrThrow("alternateMobile")),

                gst = cursor.getString(cursor.getColumnIndexOrThrow("gst")),

                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),

                address = cursor.getString(cursor.getColumnIndexOrThrow("address")),

                city = cursor.getString(cursor.getColumnIndexOrThrow("city")),

                state = cursor.getString(cursor.getColumnIndexOrThrow("state")),

                pincode = cursor.getString(cursor.getColumnIndexOrThrow("pincode")),

                openingBalance = cursor.getString(
                    cursor.getColumnIndexOrThrow("openingBalance")
                ).toDoubleOrNull() ?: 0.0,

                creditLimit = cursor.getString(
                    cursor.getColumnIndexOrThrow("creditLimit")
                ).toDoubleOrNull() ?: 0.0,

                customerType = cursor.getString(
                    cursor.getColumnIndexOrThrow("customerType")
                ),

                whatsapp = cursor.getString(
                    cursor.getColumnIndexOrThrow("whatsapp")
                ),

                birthday = cursor.getString(
                    cursor.getColumnIndexOrThrow("birthday")
                ),

                anniversary = cursor.getString(
                    cursor.getColumnIndexOrThrow("anniversary")
                ),

                notes = cursor.getString(
                    cursor.getColumnIndexOrThrow("notes")
                ),

                status = cursor.getString(
                    cursor.getColumnIndexOrThrow("status")
                )

            )

        }

        cursor.close()
        db.close()

        return customer
    }

    fun getAllCustomers(): MutableList<Customer> {

        val customerList = mutableListOf<Customer>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Customers ORDER BY customerName ASC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                customerList.add(

                    Customer(

                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                        customerName = cursor.getString(cursor.getColumnIndexOrThrow("customerName")),

                        mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile")),

                        alternateMobile = cursor.getString(cursor.getColumnIndexOrThrow("alternateMobile")),

                        gst = cursor.getString(cursor.getColumnIndexOrThrow("gst")),

                        email = cursor.getString(cursor.getColumnIndexOrThrow("email")),

                        address = cursor.getString(cursor.getColumnIndexOrThrow("address")),

                        city = cursor.getString(cursor.getColumnIndexOrThrow("city")),

                        state = cursor.getString(cursor.getColumnIndexOrThrow("state")),

                        pincode = cursor.getString(cursor.getColumnIndexOrThrow("pincode")),

                        openingBalance = cursor.getString(
                            cursor.getColumnIndexOrThrow("openingBalance")
                        ).toDoubleOrNull() ?: 0.0,

                        creditLimit = cursor.getString(
                            cursor.getColumnIndexOrThrow("creditLimit")
                        ).toDoubleOrNull() ?: 0.0,

                        customerType = cursor.getString(
                            cursor.getColumnIndexOrThrow("customerType")
                        ),

                        whatsapp = cursor.getString(
                            cursor.getColumnIndexOrThrow("whatsapp")
                        ),

                        birthday = cursor.getString(
                            cursor.getColumnIndexOrThrow("birthday")
                        ),

                        anniversary = cursor.getString(
                            cursor.getColumnIndexOrThrow("anniversary")
                        ),

                        notes = cursor.getString(
                            cursor.getColumnIndexOrThrow("notes")
                        ),

                        status = cursor.getString(
                            cursor.getColumnIndexOrThrow("status")
                        )

                    )

                )

            } while (cursor.moveToNext())

        }


        cursor.close()
        db.close()

        return customerList
    }
//--------------------------------------------------
// PRODUCT FUNCTIONS
//--------------------------------------------------

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

        specialRate: String,
        specialDiscount: String,

        stock: String,

        description: String,

        image1: String,
        image2: String,
        image3: String,
        image4: String,
        image5: String,

        videoUrl: String,
        pdfUrl: String,

        offerTitle: String,
        offerStart: String,
        offerEnd: String,

        isNewArrival: Boolean,
        isFeatured: Boolean,
        isBestSeller: Boolean,
        isSpecial: Boolean,
        isFestivalOffer: Boolean,
        isComingSoon: Boolean,

        status: String

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

        values.put("specialRate", specialRate)
        values.put("specialDiscount", specialDiscount)

        values.put("stock", stock)

        values.put("description", description)

        values.put("image1", image1)
        values.put("image2", image2)
        values.put("image3", image3)
        values.put("image4", image4)
        values.put("image5", image5)

        values.put("videoUrl", videoUrl)
        values.put("pdfUrl", pdfUrl)

        values.put("offerTitle", offerTitle)
        values.put("offerStart", offerStart)
        values.put("offerEnd", offerEnd)

        values.put("isNewArrival", if (isNewArrival) 1 else 0)
        values.put("isFeatured", if (isFeatured) 1 else 0)
        values.put("isBestSeller", if (isBestSeller) 1 else 0)
        values.put("isSpecial", if (isSpecial) 1 else 0)
        values.put("isFestivalOffer", if (isFestivalOffer) 1 else 0)
        values.put("isComingSoon", if (isComingSoon) 1 else 0)

        values.put("status", status)

        val result = db.insert(
            "Products",
            null,
            values
        )

        db.close()

        return result != -1L
    }

    fun updateProduct(

        id: Int,

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

        specialRate: String,
        specialDiscount: String,

        stock: String,

        description: String,

        image1: String,
        image2: String,
        image3: String,
        image4: String,
        image5: String,

        videoUrl: String,
        pdfUrl: String,

        offerTitle: String,
        offerStart: String,
        offerEnd: String,

        isNewArrival: Boolean,
        isFeatured: Boolean,
        isBestSeller: Boolean,
        isSpecial: Boolean,
        isFestivalOffer: Boolean,
        isComingSoon: Boolean,

        status: String

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

        values.put("specialRate", specialRate)
        values.put("specialDiscount", specialDiscount)

        values.put("stock", stock)

        values.put("description", description)

        values.put("image1", image1)
        values.put("image2", image2)
        values.put("image3", image3)
        values.put("image4", image4)
        values.put("image5", image5)

        values.put("videoUrl", videoUrl)
        values.put("pdfUrl", pdfUrl)

        values.put("offerTitle", offerTitle)
        values.put("offerStart", offerStart)
        values.put("offerEnd", offerEnd)

        values.put("isNewArrival", if (isNewArrival) 1 else 0)
        values.put("isFeatured", if (isFeatured) 1 else 0)
        values.put("isBestSeller", if (isBestSeller) 1 else 0)
        values.put("isSpecial", if (isSpecial) 1 else 0)
        values.put("isFestivalOffer", if (isFestivalOffer) 1 else 0)
        values.put("isComingSoon", if (isComingSoon) 1 else 0)

        values.put("status", status)

        val result = db.update(
            "Products",
            values,
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun getProductById(id: Int): Product? {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Products WHERE id=?",
            arrayOf(id.toString())
        )
        var product: Product? = null

        if (cursor.moveToFirst()) {

            product = Product(

                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                productName = cursor.getString(cursor.getColumnIndexOrThrow("productName")),

                productCode = cursor.getString(cursor.getColumnIndexOrThrow("productCode")),

                category = cursor.getString(cursor.getColumnIndexOrThrow("category")),

                company = cursor.getString(cursor.getColumnIndexOrThrow("company")),

                hsnCode = cursor.getString(cursor.getColumnIndexOrThrow("hsnCode")),

                gstRate = cursor.getString(cursor.getColumnIndexOrThrow("gstRate")),

                purchaseRate = cursor.getString(cursor.getColumnIndexOrThrow("purchaseRate")),

                saleRate = cursor.getString(cursor.getColumnIndexOrThrow("saleRate")),

                mrp = cursor.getString(cursor.getColumnIndexOrThrow("mrp")),

                dealerRate = cursor.getString(cursor.getColumnIndexOrThrow("dealerRate")),

                specialRate = cursor.getString(cursor.getColumnIndexOrThrow("specialRate")),

                specialDiscount = cursor.getString(cursor.getColumnIndexOrThrow("specialDiscount")),

                stock = cursor.getString(cursor.getColumnIndexOrThrow("stock")),

                description = cursor.getString(cursor.getColumnIndexOrThrow("description")),

                image1 = cursor.getString(cursor.getColumnIndexOrThrow("image1")),

                image2 = cursor.getString(cursor.getColumnIndexOrThrow("image2")),

                image3 = cursor.getString(cursor.getColumnIndexOrThrow("image3")),

                image4 = cursor.getString(cursor.getColumnIndexOrThrow("image4")),

                image5 = cursor.getString(cursor.getColumnIndexOrThrow("image5")),

                videoUrl = cursor.getString(cursor.getColumnIndexOrThrow("videoUrl")),

                pdfUrl = cursor.getString(cursor.getColumnIndexOrThrow("pdfUrl")),

                offerTitle = cursor.getString(cursor.getColumnIndexOrThrow("offerTitle")),

                offerStart = cursor.getString(cursor.getColumnIndexOrThrow("offerStart")),

                offerEnd = cursor.getString(cursor.getColumnIndexOrThrow("offerEnd")),

                isNewArrival = cursor.getInt(cursor.getColumnIndexOrThrow("isNewArrival")) == 1,

                isFeatured = cursor.getInt(cursor.getColumnIndexOrThrow("isFeatured")) == 1,

                isBestSeller = cursor.getInt(cursor.getColumnIndexOrThrow("isBestSeller")) == 1,

                isSpecial = cursor.getInt(cursor.getColumnIndexOrThrow("isSpecial")) == 1,

                isFestivalOffer = cursor.getInt(cursor.getColumnIndexOrThrow("isFestivalOffer")) == 1,

                isComingSoon = cursor.getInt(cursor.getColumnIndexOrThrow("isComingSoon")) == 1,

                status = cursor.getString(cursor.getColumnIndexOrThrow("status"))

            )
        }

        cursor.close()
        db.close()

        return product
    }

    fun getAllProducts(): MutableList<Product> {

        val productList = mutableListOf<Product>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Products ORDER BY productName ASC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                productList.add(

                    Product(

                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                        productName = cursor.getString(cursor.getColumnIndexOrThrow("productName")),

                        productCode = cursor.getString(cursor.getColumnIndexOrThrow("productCode")),

                        category = cursor.getString(cursor.getColumnIndexOrThrow("category")),

                        company = cursor.getString(cursor.getColumnIndexOrThrow("company")),

                        hsnCode = cursor.getString(cursor.getColumnIndexOrThrow("hsnCode")),

                        gstRate = cursor.getString(cursor.getColumnIndexOrThrow("gstRate")),

                        purchaseRate = cursor.getString(cursor.getColumnIndexOrThrow("purchaseRate")),

                        saleRate = cursor.getString(cursor.getColumnIndexOrThrow("saleRate")),

                        mrp = cursor.getString(cursor.getColumnIndexOrThrow("mrp")),

                        dealerRate = cursor.getString(cursor.getColumnIndexOrThrow("dealerRate")),

                        specialRate = cursor.getString(cursor.getColumnIndexOrThrow("specialRate")),

                        specialDiscount = cursor.getString(cursor.getColumnIndexOrThrow("specialDiscount")),

                        stock = cursor.getString(cursor.getColumnIndexOrThrow("stock")),

                        description = cursor.getString(cursor.getColumnIndexOrThrow("description")),

                        image1 = cursor.getString(cursor.getColumnIndexOrThrow("image1")),

                        image2 = cursor.getString(cursor.getColumnIndexOrThrow("image2")),

                        image3 = cursor.getString(cursor.getColumnIndexOrThrow("image3")),

                        image4 = cursor.getString(cursor.getColumnIndexOrThrow("image4")),

                        image5 = cursor.getString(cursor.getColumnIndexOrThrow("image5")),

                        videoUrl = cursor.getString(cursor.getColumnIndexOrThrow("videoUrl")),

                        pdfUrl = cursor.getString(cursor.getColumnIndexOrThrow("pdfUrl")),

                        offerTitle = cursor.getString(cursor.getColumnIndexOrThrow("offerTitle")),

                        offerStart = cursor.getString(cursor.getColumnIndexOrThrow("offerStart")),

                        offerEnd = cursor.getString(cursor.getColumnIndexOrThrow("offerEnd")),

                        isNewArrival = cursor.getInt(cursor.getColumnIndexOrThrow("isNewArrival")) == 1,

                        isFeatured = cursor.getInt(cursor.getColumnIndexOrThrow("isFeatured")) == 1,

                        isBestSeller = cursor.getInt(cursor.getColumnIndexOrThrow("isBestSeller")) == 1,

                        isSpecial = cursor.getInt(cursor.getColumnIndexOrThrow("isSpecial")) == 1,

                        isFestivalOffer = cursor.getInt(cursor.getColumnIndexOrThrow("isFestivalOffer")) == 1,

                        isComingSoon = cursor.getInt(cursor.getColumnIndexOrThrow("isComingSoon")) == 1,

                        status = cursor.getString(cursor.getColumnIndexOrThrow("status"))

                    )

                )

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return productList
    }//--------------------------------------------------
// PRODUCT EXTRA FUNCTIONS
//--------------------------------------------------

    fun deleteProduct(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Products",
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun deleteAllProducts(): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "Products",
            null,
            null
        )

        db.close()

        return result >= 0
    }

    fun isProductExists(productCode: String): Boolean {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT id FROM Products WHERE productCode=?",
            arrayOf(productCode)
        )

        val exists = cursor.count > 0

        cursor.close()
        db.close()

        return exists
    }

    fun getNewArrivalProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isNewArrival }
            .toMutableList()
    }

    fun getFeaturedProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isFeatured }
            .toMutableList()
    }

    fun getBestSellerProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isBestSeller }
            .toMutableList()
    }

    fun getSpecialProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isSpecial }
            .toMutableList()
    }

    fun getFestivalProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isFestivalOffer }
            .toMutableList()
    }

    fun getComingSoonProducts(): MutableList<Product> {

        return getAllProducts()
            .filter { it.isComingSoon }
            .toMutableList()
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
                        imageUrl = cursor.getString(7),
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

    //--------------------------------------------------
    // COMPANY FUNCTIONS
    //--------------------------------------------------

    fun insertCompany(
        companyCode: String,
        companyName: String,
        discount: Double,
        gst: String,
        phone: String,
        email: String,
        address: String,
        website: String,
        logo: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put("companyCode", companyCode)
            put("companyName", companyName)

            put("discount", discount)

            put("gst", gst)
            put("phone", phone)
            put("email", email)

            put("address", address)
            put("website", website)

            put("logo", logo)

            put("status", status)
        }

        val result = db.insert("Companies", null, values)
        db.close()

        return result != -1L
    }

    fun updateCompany(

        id: Int,

        companyCode: String,
        companyName: String,

        discount: Double,

        gst: String,
        phone: String,
        email: String,

        address: String,
        website: String,

        logo: String,

        status: String

    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {
            put("companyCode", companyCode)
            put("companyName", companyName)
            put("discount", discount)
            put("gst", gst)
            put("phone", phone)
            put("email", email)
            put("address", address)
            put("website", website)
            put("status", status)
            put("logo", logo)
        }

        val result = db.update(
            "Companies",
            values,
            "id=?",
            arrayOf(id.toString())
        )
        db.close()

        return result > 0
    }

    fun getAllCompanies(): MutableList<Company> {

        val companyList = mutableListOf<Company>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Companies ORDER BY companyName ASC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                companyList.add(

                    Company(

                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                        companyCode = cursor.getString(cursor.getColumnIndexOrThrow("companyCode")),

                        companyName = cursor.getString(cursor.getColumnIndexOrThrow("companyName")),

                        discount = cursor.getDouble(cursor.getColumnIndexOrThrow("discount")),

                        gst = cursor.getString(cursor.getColumnIndexOrThrow("gst")),

                        phone = cursor.getString(cursor.getColumnIndexOrThrow("phone")),

                        email = cursor.getString(cursor.getColumnIndexOrThrow("email")),

                        address = cursor.getString(cursor.getColumnIndexOrThrow("address")),

                        website = cursor.getString(cursor.getColumnIndexOrThrow("website")),

                        logo = cursor.getString(cursor.getColumnIndexOrThrow("logo")),

                        status = cursor.getString(cursor.getColumnIndexOrThrow("status"))

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
    fun insertCategory(
        categoryCode: String,
        categoryName: String,
        company: String,
        description: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("categoryCode", categoryCode)
        values.put("categoryName", categoryName)
        values.put("company", company)
        values.put("description", description)
        values.put("status", status)

        return db.insert("Categories", null, values) != -1L
    }
    fun updateCategory(
        id: Int,
        categoryCode: String,
        categoryName: String,
        company: String,
        description: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("categoryCode", categoryCode)
        values.put("categoryName", categoryName)
        values.put("company", company)
        values.put("description", description)
        values.put("status", status)

        return db.update(
            "Categories",
            values,
            "id=?",
            arrayOf(id.toString())
        ) > 0
    }
    fun deleteCategory(id: Int): Boolean {

        val db = writableDatabase

        return db.delete(
            "Categories",
            "id=?",
            arrayOf(id.toString())
        ) > 0
    }
    fun getAllCategories(): List<Category> {

        val list = mutableListOf<Category>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Categories ORDER BY categoryName",
            null
        )

        while (cursor.moveToNext()) {

            list.add(

                Category(

                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),

                    categoryCode = cursor.getString(cursor.getColumnIndexOrThrow("categoryCode")),

                    categoryName = cursor.getString(cursor.getColumnIndexOrThrow("categoryName")),

                    company = cursor.getString(cursor.getColumnIndexOrThrow("company")),

                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),

                    status = cursor.getString(cursor.getColumnIndexOrThrow("status"))

                )
            )
        }

        cursor.close()

        return list
    }fun insertOrder(order: Order): Long {

        val db = writableDatabase

        val values = ContentValues().apply {

            put("orderNo", order.orderNo)
            put("customerId", order.customerId)
            put("customerName", order.customerName)
            put("mobile", order.mobile)
            put("address", order.address)
            put("orderDate", order.orderDate)
            put("subTotal", order.subTotal)
            put("discount", order.discount)
            put("gst", order.gst)
            put("grandTotal", order.grandTotal)
            put("paymentMode", order.paymentMode)
            put("paymentStatus", order.paymentStatus)
            put("orderStatus", order.orderStatus)

        }

        val id = db.insert("Orders", null, values)

        db.close()

        return id
    }fun insertOrderItem(item: OrderItem): Long {

        val db = writableDatabase

        val values = ContentValues().apply {

            put("orderId", item.orderId)
            put("productId", item.productId)
            put("productName", item.productName)
            put("productCode", item.productCode)
            put("quantity", item.quantity)
            put("rate", item.rate)
            put("gst", item.gst)
            put("discount", item.discount)
            put("amount", item.amount)

        }

        val id = db.insert("OrderItems", null, values)

        db.close()

        return id
    }fun getAllOrders(): MutableList<Order> {

        val list = mutableListOf<Order>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Orders ORDER BY id DESC",
            null
        )

        while (cursor.moveToNext()) {

            list.add(

                Order(

                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    orderNo = cursor.getString(cursor.getColumnIndexOrThrow("orderNo")),
                    customerId = cursor.getInt(cursor.getColumnIndexOrThrow("customerId")),
                    customerName = cursor.getString(cursor.getColumnIndexOrThrow("customerName")),
                    mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile")),
                    address = cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    orderDate = cursor.getString(cursor.getColumnIndexOrThrow("orderDate")),
                    subTotal = cursor.getDouble(cursor.getColumnIndexOrThrow("subTotal")),
                    discount = cursor.getDouble(cursor.getColumnIndexOrThrow("discount")),
                    gst = cursor.getDouble(cursor.getColumnIndexOrThrow("gst")),
                    grandTotal = cursor.getDouble(cursor.getColumnIndexOrThrow("grandTotal")),
                    paymentMode = cursor.getString(cursor.getColumnIndexOrThrow("paymentMode")),
                    paymentStatus = cursor.getString(cursor.getColumnIndexOrThrow("paymentStatus")),
                    orderStatus = cursor.getString(cursor.getColumnIndexOrThrow("orderStatus"))

                )

            )

        }

        cursor.close()
        db.close()

        return list

    }fun getOrderItems(orderId: Int): MutableList<OrderItem> {

        val list = mutableListOf<OrderItem>()

        val db = readableDatabase

        val cursor = db.rawQuery(

            "SELECT * FROM OrderItems WHERE orderId=?",

            arrayOf(orderId.toString())

        )

        while (cursor.moveToNext()) {

            list.add(

                OrderItem(

                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    orderId = cursor.getInt(cursor.getColumnIndexOrThrow("orderId")),
                    productId = cursor.getInt(cursor.getColumnIndexOrThrow("productId")),
                    productName = cursor.getString(cursor.getColumnIndexOrThrow("productName")),
                    productCode = cursor.getString(cursor.getColumnIndexOrThrow("productCode")),
                    quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                    rate = cursor.getDouble(cursor.getColumnIndexOrThrow("rate")),
                    gst = cursor.getDouble(cursor.getColumnIndexOrThrow("gst")),
                    discount = cursor.getDouble(cursor.getColumnIndexOrThrow("discount")),
                    amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"))

                )

            )

        }

        cursor.close()
        db.close()

        return list

    }fun deleteOrder(id: Int): Boolean {

        val db = writableDatabase

        db.delete(
            "OrderItems",
            "orderId=?",
            arrayOf(id.toString())
        )

        val result = db.delete(
            "Orders",
            "id=?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0

    }
}
