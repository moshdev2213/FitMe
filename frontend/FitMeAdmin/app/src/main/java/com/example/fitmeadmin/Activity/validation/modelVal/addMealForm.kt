package com.example.fitmeadmin.Activity.validation.modelVal

import com.example.fitmeadmin.Activity.validation.ValidationResult

class addMealForm (
    private var name:String,
    private var cal:String,
    private var fat:String,
    private var carbs:String
) {

    fun validatemuscle(): ValidationResult {
        return if(name.isEmpty()){
            ValidationResult.Empty("muscle  is empty")
        }else if(name.length<3){
            ValidationResult.Invalid("muscle should have at least 3 characters")
        }else{
            ValidationResult.Valid
        }
    }

    fun validatecal(): ValidationResult {
        return if(cal.isEmpty()){
            ValidationResult.Empty("muscle  is empty")
        }else if(cal.length>3){
            ValidationResult.Invalid("muscle should have at most 3 characters")
        }else{
            ValidationResult.Valid
        }
    }

    fun validatefat(): ValidationResult {
        return if(fat.isEmpty()){
            ValidationResult.Empty("fat  is empty")
        }else if(fat.length>3){
            ValidationResult.Invalid("fat should have at most 3 characters")
        }else{
            ValidationResult.Valid
        }
    }

    fun validatecarbs(): ValidationResult {
        return if(carbs.isEmpty()){
            ValidationResult.Empty("carbs  is empty")
        }else if(carbs.length>3){
            ValidationResult.Invalid("carbs should have at most 3 characters")
        }else{
            ValidationResult.Valid
        }
    }

}