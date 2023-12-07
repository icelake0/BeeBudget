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
import kotlinx.coroutines.tasks.await

class LoginScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    fun loginUser(
        email: String, password: String,
        errorCallback: () -> Unit = {},
        successCallback: () -> Unit )  = viewModelScope.launch{
        try {
            if (_loading.value == false) {
                _loading.value = true
                auth.signInWithEmailAndPassword(email, password).await()
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback()
        } finally {
            _loading.value = false
        }
    }
}