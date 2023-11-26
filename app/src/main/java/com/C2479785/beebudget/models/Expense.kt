package com.C2479785.beebudget.models

import androidx.compose.ui.graphics.Color
import com.google.firebase.firestore.QueryDocumentSnapshot

data class Expense(val id: String?,
                   val userId: String?,
                 val amount: String,
                 val description: String,
                 val date: String,
                 val category: String) {
    fun pillColor(): Long {
        return when (this.category) {
            ExpenseCategories.Subscriptions.value -> 0xFFA2E2A5
            ExpenseCategories.Food.value -> 0xFFDF6F66
            ExpenseCategories.Groceries.value -> 0xFFE7CF85
            ExpenseCategories.Transportation.value -> 0xFF9677CE
            ExpenseCategories.Entertainment.value -> 0xFF6E97B8
            ExpenseCategories.PersonalCare.value -> 0xFFC0C77F
            ExpenseCategories.Others.value -> 0xFFCC7895
            null -> 0xFFC0C77F
            else -> 0xFFC0C77F
        }
    }
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "user_id" to this.userId,
            "amount" to this.amount,
            "description" to this.description,
            "date" to this.date,
            "category" to this.category,
            "day" to this.date.split("/")[0],
            "month" to this.date.split("/")[1],
            "year" to this.date.split("/")[2],
        )
    }

    companion object {
        fun fromQueryDocumentSnapshot(result: QueryDocumentSnapshot): Expense {
            return Expense(
                id = result.get("id").toString(),
                userId = result.get("user_id").toString(),
                amount = result.get("amount").toString(),
                description = result.get("description").toString(),
                date = result.get("date").toString(),
                category = result.get("category").toString()
            )
        }

        fun nullInstance(): Expense {
            return Expense(
                id = null,
                userId = null,
                amount = "xx.xx",
                description = "loading...",
                date = "loading...",
                category = "loading..."
            )
        }
    }
}

