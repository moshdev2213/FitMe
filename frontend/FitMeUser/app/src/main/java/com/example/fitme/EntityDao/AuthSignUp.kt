package com.example.fitme.EntityDao

import java.io.Serializable

data class AuthSignUp(
    val telephone:String,
    val email: String,
    val password:String,
    val passwordConfirm:String,
):Serializable
