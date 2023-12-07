package com.C2479785.beebudget.screens.budget

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.C2479785.beebudget.models.Budget
import com.C2479785.beebudget.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.util.UUID

class BudgetScreenViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var _loadedBudget = MutableLiveData(false)
    val loadedBudget: LiveData<Boolean> = _loadedBudget


    private val _currentBudget = MutableLiveData<Budget?>(null)
    val currentBudget : LiveData<Budget?> = _currentBudget


    val months = listOf<String> (
        "January","February","March",
        "April","May","June",
        "July","August","September",
        "October","November","December "
    )

    val years = listOf<String> (
        "2021","2022","2023", "2024", "2025"
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun geBudgetForForm(
        selectedMonth: Int = LocalDate.now().month.value -1,
        selectedYear: Int = LocalDate.now().year,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit
    ) = viewModelScope.launch {
        try {
            if(_loading.value == false) {
                _loading.value = true
                val userId = auth.currentUser?.uid
                val budgets = FirebaseFirestore
                    .getInstance()
                    .collection("budget")
                    .whereEqualTo("user_id", userId)
                    .whereEqualTo("month", selectedMonth)
                    .whereEqualTo("year", years[selectedYear].toInt())
                    .get().await()
                if(!budgets.isEmpty) {
                     _currentBudget.value =  Budget.fromQueryDocumentSnapshot(budgets.first())
                    successCallback()
                }
                else {
                    val budgets = FirebaseFirestore
                        .getInstance()
                        .collection("budget")
                        .whereEqualTo("user_id", userId)
                        .get().await()
                    if(!budgets.isEmpty) {
                        val latestBudget = Budget.fromQueryDocumentSnapshot(budgets.first())
                        latestBudget.id = null
                        _currentBudget.value = latestBudget
                        successCallback()
                    }
                    else{
                        _currentBudget.value =  Budget.default(
                            userId = userId,
                            month = selectedMonth,
                            year = selectedYear
                        )
                        successCallback()
                    }
                }
                _loadedBudget.value = true
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _loading.value = false
        }

    }

    fun updateBudget(
        month: Int,
        year: Int,
        subscriptions: Int,
        food: Int,
        groceries: Int,
        transportation: Int,
        entertainment: Int,
        personalCare: Int,
        others: Int,
        budgetId: String? = null,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit
    ) = viewModelScope.launch{
        try {
            if (_loading.value == false) {
                _loading.value = true
                val userId = auth.currentUser?.uid
                if(budgetId !== null){
                    val docToUpdate =  FirebaseFirestore
                        .getInstance()
                        .collection("budget")
                        .whereEqualTo("id", budgetId)
                        .get().await().first()
                    FirebaseFirestore
                        .getInstance()
                        .collection("budget")
                        .document(docToUpdate.id)
                        .set(
                            Budget(
                                id = budgetId,
                                userId = userId,
                                month = month,
                                year = years[year].toInt(),
                                subscriptions = subscriptions,
                                food = food,
                                groceries = groceries,
                                transportation = transportation,
                                entertainment = entertainment,
                                personalCare = personalCare,
                                others = others
                            ).toMap()
                        ).await()
                } else{
                    val budget = Budget(
                        id = UUID.randomUUID().toString(),
                        userId = userId,
                        month = month,
                        year = years[year].toInt(),
                        subscriptions = subscriptions,
                        food = food,
                        groceries = groceries,
                        transportation = transportation,
                        entertainment = entertainment,
                        personalCare = personalCare,
                        others = others
                    ).toMap()
                    FirebaseFirestore
                        .getInstance()
                        .collection("budget")
                        .add(budget)
                }
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _loading.value = false
        }
    }

}