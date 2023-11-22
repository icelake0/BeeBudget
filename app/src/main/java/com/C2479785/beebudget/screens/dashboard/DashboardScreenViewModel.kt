package com.C2479785.beebudget.screens.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.C2479785.beebudget.models.Budget
import com.C2479785.beebudget.models.Expense
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.util.UUID

class DashboardScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private var _addingExpense = MutableLiveData(false)
    val addingExpense: LiveData<Boolean> = _addingExpense

    private var _loadingExpenses = MutableLiveData(false)
    val loadingExpenses: LiveData<Boolean> = _loadingExpenses

    private var _findingExpenseById = MutableLiveData(false)
    val findingExpenseById: LiveData<Boolean> = _findingExpenseById

    private var _expenses = MutableLiveData(listOf<Expense>())
    val expenses: LiveData<List<Expense>> = _expenses

    private var _expenseFoundById = MutableLiveData<Expense?>(null)
    val expenseFoundById: LiveData<Expense?> = _expenseFoundById

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