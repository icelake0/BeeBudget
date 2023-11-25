package com.C2479785.beebudget.models

data class DashboardSummaryDTO(
    val totalBudget: Float,
    val totalExpense: Float,
    val spendRate: Float,
    val subscriptions: Float,
    val food: Float,
    val groceries: Float,
    val transportation: Float,
    val entertainment: Float,
    val personalCare: Float,
    val others: Float
) {
}