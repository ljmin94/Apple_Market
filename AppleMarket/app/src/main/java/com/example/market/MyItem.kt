package com.example.market

data class MyItem(
    val photo: Int,
    val name: String,
    val addr: String,
    var price: String,
    val chat: Int,
    val heart: Int
) {
    val priceFormatted: String
        get() {
            return String.format("%,d", price.toInt())
        }
}