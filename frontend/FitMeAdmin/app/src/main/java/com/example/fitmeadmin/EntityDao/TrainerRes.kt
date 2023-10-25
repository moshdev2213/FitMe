package com.example.fitmeadmin.EntityDao

data class TrainerRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<TrainerItem>
)
