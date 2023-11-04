package com.C2479785.beebudget.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    DashboardScreen,
    BudgetScreen,
    ExpenseScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens
                = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            DashboardScreen.name -> DashboardScreen
            BudgetScreen.name -> BudgetScreen
            ExpenseScreen.name -> ExpenseScreen
            null -> DashboardScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}