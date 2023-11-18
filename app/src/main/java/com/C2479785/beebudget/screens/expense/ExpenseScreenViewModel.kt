package com.C2479785.beebudget.screens.expense

import androidx.lifecycle.ViewModel

class ExpenseScreenViewModel: ViewModel() {

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