package com.C2479785.beebudget.screens.expense

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.C2479785.beebudget.R
import com.C2479785.beebudget.models.Budget
import com.C2479785.beebudget.models.Expense
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.time.LocalDate
import java.util.UUID
import java.util.concurrent.ExecutorService

class ExpenseScreenViewModel: ViewModel() {

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

    private var _expensePhotoURI = MutableLiveData<Uri?>(null)
    val expensePhotoURI: LiveData<Uri?> = _expensePhotoURI

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
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _loadingExpenses.value = false
        }
    }

    fun findExpenseById(
        expenseId: String,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit = {}
    ) = viewModelScope.launch {
        try {
            if(_findingExpenseById.value == false) {
                _expenseFoundById.value = null
                _findingExpenseById.value = true
                _expenseFoundById.value = Expense.fromQueryDocumentSnapshot(
                    FirebaseFirestore
                    .getInstance()
                    .collection("expenses")
                    .whereEqualTo("id", expenseId)
                    .get().await().first()
                )
                downloadPhotoFromFireStore(_expenseFoundById.value!!)
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _findingExpenseById.value = false
        }
    }

    fun deleteExpenseById(
        expenseId: String,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit = {}
    ) = viewModelScope.launch {
        try {
            if(_findingExpenseById.value == false) {
                _expenseFoundById.value = null
                _findingExpenseById.value = true
                val docToDelete =  FirebaseFirestore
                    .getInstance()
                    .collection("expenses")
                    .whereEqualTo("id", expenseId)
                    .get().await().first()
                FirebaseFirestore
                    .getInstance()
                    .collection("expenses")
                    .document(docToDelete.id)
                    .delete()
                    .await()
                successCallback()
            }
        } catch (ex: Exception){
            errorCallback(ex.message)
        } finally {
            _findingExpenseById.value = false
        }
    }

    fun uploadPhotoToFireStore(
        photoUri: Uri,
        expenseId: String,
        errorCallback: (message : String?) -> Unit = {},
        successCallback: () -> Unit = {},
    ) = viewModelScope.launch {
        try {
            FirebaseStorage.getInstance()
                .reference.child("expense/${expenseId}.png")
                .putFile(photoUri).await()
            successCallback()
        } catch (ex: Exception){
            errorCallback(ex.message)
        }
    }

    fun downloadPhotoFromFireStore(expense: Expense) = viewModelScope.launch {
        try {
            _expensePhotoURI.value = FirebaseStorage.getInstance()
                .reference.child("expense/${expense.id}.png")
                .downloadUrl.await()
        } catch (ex: Exception){
            try{
            _expensePhotoURI.value = FirebaseStorage.getInstance()
                .reference.child("expense/default.png")
                .downloadUrl.await()
            }catch(ex: Exception){}
        }
    }

}