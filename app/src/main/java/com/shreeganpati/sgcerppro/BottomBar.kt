package com.shreeganpati.sgcerppro

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun BottomBar() {

    var selected by remember {
        mutableIntStateOf(0)
    }

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Products,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color(0xFF0D47A1)
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(

                selected = selected == index,

                onClick = {
                    selected = index
                },

                icon = {
                    Icon(item.icon, null)
                },

                label = {
                    Text(item.title)
                }

            )
        }

    }

}