package com.C2479785.beebudget.screens.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.C2479785.beebudget.models.User
import com.google.android.gms.tasks.RuntimeExecutionException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class RegisterScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    fun createUser(
        email: String, password: String,
        firstName: String, lastName: String,
        successCallback: () -> Unit )  = viewModelScope.launch{
        try {
            if (_loading.value == false) {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnFailureListener {task ->
                        Log.d("FireBase", "createUser: ${task.message}")
                    }
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid

                            val user = User(
                                userId = userId.toString(),
                                firstName = firstName.toString(),
                                lastName = lastName.toString(),
                                id = null
                            ).toMap()

                            FirebaseFirestore.getInstance().collection("users")
                                .add(user)

                            successCallback()
                        }else {
                            Log.d("FireBase", "createUser: ${task.result.toString()}")
                        }
                    }
            }
        } catch (ex: Exception){
            //ToDo show message in toast also handle existing email crashing app
            Log.d("FireBase", "createUser: ${ex.message}")
        } finally {
            _loading.value = false
        }
    }
}