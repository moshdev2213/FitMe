package com.example.fitme.EntityDao

data class WrkOutRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<ExerciseItem>
)
