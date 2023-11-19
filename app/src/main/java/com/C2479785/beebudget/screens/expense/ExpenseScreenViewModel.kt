package com.C2479785.beebudget.screens.expense

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

class ExpenseScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private var _addingExpense = MutableLiveData(false)
    val addingExpense: LiveData<Boolean> = _addingExpense

    private var _loadingExpenses = MutableLiveData(false)
    val loadingExpenses: LiveData<Boolean> = _loadingExpenses

    private var _expenses = MutableLiveData(listOf<Expense>())
    val expenses: LiveData<List<Expense>> = _expenses

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

    fun loadExpenses(
        month: String,
        year: String,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit
    ) = viewModelScope.launch {
        try {
            if(_loadingExpenses.value == false) {
                _expenses.value = listOf<Expense>()
                _loadingExpenses.value = true
                val userId = auth.currentUser?.uid
                _expenses.value = FirebaseFirestore
                    .getInstance()
                    .collection("expenses")
                    .whereEqualTo("user_id", userId)
                    .whereEqualTo("month", month)
                    .whereEqualTo("year", year)
                    .get().await().map {
                        Expense.fromQueryDocumentSnapshot(it)
                    }.toList()
                _loadingExpenses.value = true
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _loadingExpenses.value = false
        }
    }

}