package com.example.psymed_mobileapplication.ui.shared.services

import com.example.psymed_mobileapplication.ui.presentation.login.LoginRequest
import com.example.psymed_mobileapplication.ui.presentation.login.LoginResponse
import com.example.psymed_mobileapplication.ui.presentation.register.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/Auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>

    @POST("api/Auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}