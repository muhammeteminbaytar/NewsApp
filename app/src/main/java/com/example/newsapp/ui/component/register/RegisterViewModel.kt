package com.example.newsapp.ui.component.register

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel:BaseViewModel() {
    var isSuccess=MutableLiveData<Boolean>()

    init {
        isSuccess=MutableLiveData(false)
    }
    fun register(auth:FirebaseAuth,email:String, password:String,name:String,db:FirebaseFirestore){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(Activity()){ task ->
                if (task.isSuccessful) {
                    hashMapCrater(name,auth,db)
                    isSuccess.value = true
                }
            }
        }
    private fun hashMapCrater(name:String, auth: FirebaseAuth, db:FirebaseFirestore){
        val userMap= hashMapOf<String,String>()
        userMap["name"] = name
        auth.currentUser?.let { userMap.put("userID", it.uid) }
        db.collection("users").add(userMap)
            .addOnSuccessListener {

            }.addOnFailureListener {
                println(it.localizedMessage)
            }
    }
}