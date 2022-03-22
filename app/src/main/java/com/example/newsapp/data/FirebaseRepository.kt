package com.example.newsapp.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepository {
    fun getFirebaseAuth() = Firebase.auth
    fun getFirebaseFirestore() = Firebase.firestore
}