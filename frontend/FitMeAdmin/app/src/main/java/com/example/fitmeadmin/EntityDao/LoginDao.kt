package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class LoginDao(
    val email:String,
    val password:String
): Serializable

