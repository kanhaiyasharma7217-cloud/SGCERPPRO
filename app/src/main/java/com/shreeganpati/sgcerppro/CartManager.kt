package com.shreeganpati.sgcerppro

object CartManager {

    val cartItems = mutableListOf<CartItem>()

    fun addToCart(
        product: Product,
        quantity: Int
    ) {

        val existing = cartItems.find {
            it.product.id == product.id
        }

        if (existing != null) {

            existing.quantity += quantity

        } else {

            cartItems.add(
                CartItem(
                    product = product,
                    quantity = quantity
                )
            )

        }

    }

    fun removeFromCart(productId: Int) {

        cartItems.removeAll {
            it.product.id == productId
        }

    }

    fun clearCart() {

        cartItems.clear()

    }

}