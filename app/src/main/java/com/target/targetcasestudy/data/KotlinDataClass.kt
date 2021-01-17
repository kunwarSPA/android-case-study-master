package com.target.targetcasestudy.data

data class ApiData(
    val products: List<Product>
)

data class Product(
    val aisle: String,
    val description: String,
    val id: Int,
    val image_url: String,
    val regular_price: RegularPrice,
    val sale_price: SalePrice,
    val title: String
)

data class RegularPrice(
    val amount_in_cents: Int,
    val currency_symbol: String,
    val display_string: String
)

data class SalePrice(
    val amount_in_cents: Int,
    val currency_symbol: String,
    val display_string: String
)