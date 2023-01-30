package com.example.inspireus.domain

import com.example.inspireus.data.remote.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImplement(private val dataSource: AuthDataSource): AuthRepository {

    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)

    override suspend fun signUp(username: String, email: String, password: String): FirebaseUser? {
        TODO("Not yet implemented")
    }
}