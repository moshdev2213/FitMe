package com.example.fitmeadmin.EntityDao

data class MealRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<MealItem>
)
