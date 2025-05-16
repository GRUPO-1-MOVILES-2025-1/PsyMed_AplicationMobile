package com.example.psymed_mobileapplication.ui.presentation.register

data class RegisterRequest(
    val username: String,
    val name: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val gender: String,
    val phone: String,
    val ubication: String,
    val password: String
)
