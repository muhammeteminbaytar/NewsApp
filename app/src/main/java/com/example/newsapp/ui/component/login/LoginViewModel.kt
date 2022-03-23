package com.example.newsapp.ui.component.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.FirebaseRepository
import com.example.newsapp.ui.base.BaseViewModel
import com.google.firebase.auth.GoogleAuthProvider


class LoginViewModel:BaseViewModel() {

    var loginStatus=MutableLiveData<Int>()
    var failMessage=MutableLiveData<String>()
    var googleLoginStatus=MutableLiveData<Boolean>()

    fun login(email:String,pass:String){
        if (email==""||pass==""){
            loginStatus.value=1
        }else{
            FirebaseRepository().getFirebaseAuth().signInWithEmailAndPassword(email,pass).addOnCompleteListener { task->
                if (task.isSuccessful){
                    loginStatus.value=3
                }
            }.addOnFailureListener {
                loginStatus.value=2
                failMessage.value=it.localizedMessage
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String,act:Activity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseRepository().getFirebaseAuth().signInWithCredential(credential)
            .addOnCompleteListener(act) { task ->
                googleLoginStatus.value = task.isSuccessful
            }
    }

}