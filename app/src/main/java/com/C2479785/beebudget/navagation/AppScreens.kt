package com.C2479785.beebudget.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    DashboardScreen,
    BudgetScreen,
    ExpenseScreen,
    AddExpenseScreen,
    ViewExpenseScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens
                = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            DashboardScreen.name -> DashboardScreen
            BudgetScreen.name -> BudgetScreen
            ExpenseScreen.name -> ExpenseScreen
            AddExpenseScreen.name -> AddExpenseScreen
            ViewExpenseScreen.name -> ViewExpenseScreen
            null -> DashboardScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}