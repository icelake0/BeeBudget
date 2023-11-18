package com.C2479785.beebudget.models

data class Expense(val id: String?,
                 val amount: String,
                 val description: String,
                 val date: String,
                 val category: String) {
    fun pillColor(): Long {
        return when (this.category) {
            ExpenseCategories.Subscriptions.value -> 0xFF4CAF50
            ExpenseCategories.Food.value -> 0xFF7C4DFF
            ExpenseCategories.Groceries.value -> 0xFFFFC300
            ExpenseCategories.Transportation.value -> 0xFF795548
            ExpenseCategories.Entertainment.value -> 0xFFC70039
            ExpenseCategories.PersonalCare.value -> 0xFF1DC5C5
            ExpenseCategories.Others.value -> 0xFF786CE2
            null -> 0xFF9CCC65
            else -> 0xFF9CCC65
        }
    }
}

