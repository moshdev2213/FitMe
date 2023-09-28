package com.example.fitme.Validation

sealed class ValidationResult{
    data class Empty(val errorMsg : String):ValidationResult()
    data class Invalid(val errorMsg: String):ValidationResult()
    object Valid:ValidationResult()
}
