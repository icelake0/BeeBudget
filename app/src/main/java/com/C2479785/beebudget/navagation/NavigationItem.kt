package com.C2479785.beebudget.navagation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.C2479785.beebudget.navigation.AppScreens

sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object Budget : NavigationItem(AppScreens.BudgetScreen.name, Icons.Rounded.Settings, "Budget")
    object Dashboard : NavigationItem(AppScreens.DashboardScreen.name, Icons.Rounded.Home, "Dashboard")
    object Expense : NavigationItem(AppScreens.ExpenseScreen.name, Icons.Rounded.ShoppingCart, "Expense")
    object AddExpense : NavigationItem(AppScreens.AddExpenseScreen.name, Icons.Rounded.ShoppingCart, "Add Expense")
    object ViewExpense : NavigationItem(AppScreens.ViewExpenseScreen.name, Icons.Rounded.ShoppingCart, "View Expense")
}