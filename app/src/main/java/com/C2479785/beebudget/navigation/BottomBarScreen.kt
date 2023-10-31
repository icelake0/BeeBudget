package com.C2479785.beebudget.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dashboard : BottomBarScreen(
        route = AppScreens.DashboardScreen.name,
        title = "Dashboard",
        icon = Icons.Default.Home
    )

    object Budget : BottomBarScreen(
        route = AppScreens.BudgetScreen.name,
        title = "Budget",
        icon = Icons.Default.Settings
    )

    object Expense : BottomBarScreen(
        route = AppScreens.BudgetScreen.name,
        title = "Expense",
        icon = Icons.Default.Add
    )
}