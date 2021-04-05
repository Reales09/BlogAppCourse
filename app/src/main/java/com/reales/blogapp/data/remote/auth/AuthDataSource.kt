package com.reales.blogapp.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.reales.blogapp.data.model.User
import kotlinx.coroutines.tasks.await

class AuthDataSource {

    suspend fun signIn(email: String,password: String):FirebaseUser?{
        val authResult=FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).await()
        return authResult.user
    }

   suspend fun signUp(usernarme: String, email: String, password: String): FirebaseUser? {
       val authResult=FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).await()
       authResult.user?.uid?.let {uid ->
           FirebaseFirestore.getInstance().collection("users").document(uid).set(User(email,usernarme,"Foto_URL.PNG")).await()
       }

       return authResult.user
    }
}