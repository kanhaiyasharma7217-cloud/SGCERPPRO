package com.shreeganpati.sgcerppro

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "SGC_ERP.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE Customer(
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
                openingBalance REAL,
                creditLimit REAL
            )
            """.trimIndent()
        )

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL("DROP TABLE IF EXISTS Customer")

        onCreate(db)
        fun addCustomer(customer: Customer): Boolean {

            val db = writableDatabase

            val values = android.content.ContentValues()

            values.put("customerName", customer.customerName)
            values.put("mobile", customer.mobile)
            values.put("alternateMobile", customer.alternateMobile)
            values.put("gst", customer.gst)
            values.put("email", customer.email)
            values.put("address", customer.address)
            values.put("city", customer.city)
            values.put("state", customer.state)
            values.put("pincode", customer.pincode)
            values.put("openingBalance", customer.openingBalance)
            values.put("creditLimit", customer.creditLimit)

            val result = db.insert("Customer", null, values)

            db.close()

            return result != -1L
        }
    }
}
