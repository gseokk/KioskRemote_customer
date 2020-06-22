package com.example.kioskremote.dto

import java.security.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime

class FoodData(var image: Int, var title: String, var description: String)

data class Store(
    val name: String? = null,
    val menuList: List<Menu>? = null,
    val table: List<Table>? = null,
    val location: String? = null
)

data class Menu(
    val name: String? = null,
    val price: Int? = null,
    val image: String? = null,
    val menuAbout: String? = null,
    val menuRatings: List<MenuRating>? = null
)

data class MenuRating(
    val rating: Int? = null,
    val review: String? = null
)

data class Table(
    val num: Int? = null,
    val availability: Boolean? = null
)

data class Order(
    val name: String? = null,
    val table: Int? = null,
    val menu: List<Pair<String, Int>>? = null,
    val timestamp: LocalDateTime

)