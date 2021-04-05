package com.reales.blogapp.domain.home.auth

import com.google.firebase.auth.FirebaseUser
import com.reales.blogapp.data.remote.auth.AuthDataSource


class AuthRepoImpl(private val dataSource: AuthDataSource):AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
    override suspend fun signUp(email: String, password: String, usernarme: String): FirebaseUser? = dataSource.signUp(usernarme,email,password)
}