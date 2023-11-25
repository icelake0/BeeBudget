package com.C2479785.beebudget.models

import com.google.firebase.firestore.QueryDocumentSnapshot

enum class ExpenseCategories (val value : String) {
    Subscriptions ("Subscriptions"),
    Food ("Food"),
    Groceries  ("Groceries"),
    Transportation  ("Transportation"),
    Entertainment ("Entertainment"),
    PersonalCare ("Personal Care"),
    Others ("Others");

    companion object {
        fun getList(): List<String> {
            return listOf(
                ExpenseCategories.Subscriptions.value,
                ExpenseCategories.Food.value,
                ExpenseCategories.Groceries.value,
                ExpenseCategories.Transportation.value,
                ExpenseCategories.Entertainment.value,
                ExpenseCategories.PersonalCare.value,
                ExpenseCategories.Others.value,
            )
        }
    }
}