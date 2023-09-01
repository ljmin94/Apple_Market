package com.example.market

data class MyDetailItem(
    val item: Int,
    val profile: Int,
    val nickname: String,
    val address: String,
    val title: String,
    val info: String,
    var d_price: String
) {
    val priceFormatted: String
        get() {
            return String.format("%,d", d_price.toInt())
        }
}