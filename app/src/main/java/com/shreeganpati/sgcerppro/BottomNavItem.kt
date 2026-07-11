package com.shreeganpati.sgcerppro

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNavItem(
        "Home",
        Icons.Default.Home
    )

    object Products : BottomNavItem(
        "Products",
        Icons.Default.Inventory
    )

    object Cart : BottomNavItem(
        "Cart",
        Icons.Default.ShoppingCart
    )

    object Profile : BottomNavItem(
        "Profile",
        Icons.Default.Person
    )
}