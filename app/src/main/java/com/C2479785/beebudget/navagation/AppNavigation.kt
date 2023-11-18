package com.C2479785.beebudget.navagation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.budget.BudgetScreen
import com.C2479785.beebudget.screens.dashboard.DashboardScreen
import com.C2479785.beebudget.screens.expense.AddExpenseScreen
import com.C2479785.beebudget.screens.expense.ExpenseScreen
import com.C2479785.beebudget.screens.login.LoginScreen
import com.C2479785.beebudget.screens.register.RegisterScreen
import com.C2479785.beebudget.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.name ) {

        composable(AppScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(AppScreens.RegisterScreen.name){
            RegisterScreen(navController = navController)
        }
        composable(AppScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(AppScreens.DashboardScreen.name){
            DashboardScreen(navController = navController)
        }
        composable(AppScreens.BudgetScreen.name){
            BudgetScreen(navController = navController)
        }
        composable(AppScreens.ExpenseScreen.name){
            ExpenseScreen(navController = navController)
        }
        composable(AppScreens.AddExpenseScreen.name){
            AddExpenseScreen(navController = navController)
        }
    }

}