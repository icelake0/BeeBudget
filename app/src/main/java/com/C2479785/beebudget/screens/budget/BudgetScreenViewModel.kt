package com.C2479785.beebudget.screens.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class BudgetScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading


    val months = listOf<String> (
        "January","February","March",
        "April","May","June",
        "July","August","September",
        "October","November","December "
    )

    val years = listOf<String> (
        "2021","2022","2023", "2024", "2025"
    )

}