package com.example.newsapp.ui.component.register

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.newsapp.R
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
                    hashMapCreater(name,auth,db)
                    isSuccess.value = true
                }
            }
        }
    private fun hashMapCreater(name:String,auth: FirebaseAuth,db:FirebaseFirestore){
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