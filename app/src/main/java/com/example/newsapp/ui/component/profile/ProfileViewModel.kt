package com.example.newsapp.ui.component.profile

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.FirebaseRepository
import com.example.newsapp.ui.base.BaseViewModel

class ProfileViewModel:BaseViewModel() {
    var name=MutableLiveData<String>()
    init {
        name=MutableLiveData("")
    }
     fun getFirestoreData(){
        with(FirebaseRepository()){
            getFirebaseFirestore().collection("users").whereEqualTo("userID",getFirebaseAuth().currentUser?.uid)
            .addSnapshotListener { value, _ ->
                name.value= value?.documents?.get(0)?.get("name").toString()
            }
        }
    }
    fun accSignOut() = FirebaseRepository().getFirebaseAuth().signOut()
}