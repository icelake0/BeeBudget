package com.C2479785.beebudget.models

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Expense(val id: String?,
                   val userId: String?,
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

