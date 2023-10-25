package com.example.fitmeadmin.Activity.validation

sealed class ValidationResult{
    data class Empty(val errorMessage:String): ValidationResult()
    data class Invalid(val errorMessage: String): ValidationResult()
    object Valid: ValidationResult()
}

