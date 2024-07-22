package com.example.todolistapp.utils

import com.example.todolistapp.utils.NumberConstants

fun fullNameLengthValidation(fullName: String): Boolean {

    return fullName.length >= NumberConstants.MinFullNameLength.value
            && fullName.length <= NumberConstants.MaxFullNameLength.value
}

fun emailLengthValidation(email: String): Boolean {

    return email.length >= NumberConstants.MinEmailLength.value
            && email.length <= NumberConstants.MaxEmailLength.value
}

fun passwordLengthValidation(password: String): Boolean {

    return password.length >= NumberConstants.MinPasswordLength.value
            && password.length <= NumberConstants.MaxPasswordLength.value
}