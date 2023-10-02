package com.example.fitme.FormData

import com.example.fitme.Validation.ValidationResult

class UserPayment(
    private var cvc:String,
    private var cNum:String,
    private var cName:String,
    private var cExpire:String
) {
    fun validateCardName(): ValidationResult {
        val regex = "^[a-zA-Z]+$"

        return if(cName.isEmpty()){
            ValidationResult.Empty("Please Enter Name")
        }else if(!cName.matches(regex.toRegex())){
            ValidationResult.Invalid("Please Enter Valid Name")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateCardNumber(): ValidationResult {
        val cardNumberRegexList = listOf(
            "^[0-9]{16}$",                  // 16-digit card number (e.g., 1234567890123456)
            "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", // Card number with hyphens (e.g., 1234-5678-9012-3456)
            "^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$", // Card number with spaces (e.g., 1234 5678 9012 3456)
            "^[0-9]{4}_[0-9]{4}_[0-9]{4}_[0-9]{4}$", // Card number with underscores (e.g., 1234_5678_9012_3456)
            "^[0-9]{4}\\.[0-9]{4}\\.[0-9]{4}\\.[0-9]{4}$", // Card number with dots (e.g., 1234.5678.9012.3456)
            "^[0-9]{15}$",                  // 15-digit card number (e.g., 987654321098765)
            "^[0-9]{13}$",                  // 13-digit card number (e.g., 8765432109876)
            "^[0-9]{14}$",                  // 14-digit card number (e.g., 76543210987654)
            "^[0-9]{12}$",                  // 12-digit card number (e.g., 654321098765)
            "^[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"  // Card number with various digits and hyphens (e.g., 123-4567-8901-2345)
        )

        for (regexPattern in cardNumberRegexList){
            if(cNum.matches(regexPattern.toRegex())){
                return ValidationResult.Valid
            }
        }
        return if(cName.isEmpty()){
            ValidationResult.Empty("Please Enter Card Number")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateCvc(): ValidationResult {
        val cvcRegex = "^[0-9]{3}$"

        return if(cvc.isEmpty()){
            ValidationResult.Empty("Please Enter cvc")
        }else if(!cvc.matches(cvcRegex.toRegex())){
            ValidationResult.Invalid("Please Enter Valid cvc")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateCardExpiration(): ValidationResult {
        val expirationDateRegex = "^(0[1-9]|1[0-2])/(2[2-9]|[3-9][0-9])$"

        return if(cExpire.isEmpty()){
            ValidationResult.Empty("Please Enter date")
        }else if(!cExpire.matches(expirationDateRegex.toRegex())){
            ValidationResult.Invalid("Please Enter Valid date")
        }else{
            ValidationResult.Valid
        }
    }
}