package com.example.fitmeadmin.EntityDao

data class UserRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<UserItem>
)
