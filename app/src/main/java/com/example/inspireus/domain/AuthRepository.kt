package com.example.inspireus.domain

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(email: String, password: String): FirebaseUser?

    suspend fun signUp(username: String, email: String, password: String): FirebaseUser?

}