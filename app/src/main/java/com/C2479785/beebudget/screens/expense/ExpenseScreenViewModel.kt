package com.C2479785.beebudget.screens.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.C2479785.beebudget.models.Expense
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.util.UUID

class ExpenseScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var _addingExpense = MutableLiveData(false)
    val addingExpense: LiveData<Boolean> = _addingExpense

    val months = listOf<String> (
        "January","February","March",
        "April","May","June",
        "July","August","September",
        "October","November","December "
    )

    val years = listOf<String> (
        "2021","2022","2023", "2024", "2025"
    )

    fun addExpense(
        amount: String,
        description: String,
        date: String,
        category: String,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit
    ) = viewModelScope.launch{
        try {
            if (_addingExpense.value == false) {
                _addingExpense.value = true
                val userId = auth.currentUser?.uid
                val expense = Expense(
                    id = UUID.randomUUID().toString(),
                    userId = userId,
                    amount = amount,
                    description = description,
                    date = date,
                    category = category,
                ).toMap()

                FirebaseFirestore.getInstance().collection("expenses")
                    .add(expense)

                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _addingExpense.value = false
        }
    }

}