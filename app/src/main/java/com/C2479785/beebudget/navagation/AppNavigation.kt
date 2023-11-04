package com.C2479785.beebudget.navagation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.BudgetScreen
import com.C2479785.beebudget.screens.DashboardScreen
import com.C2479785.beebudget.screens.ExpenseScreen
import com.C2479785.beebudget.screens.LoginScreen
import com.C2479785.beebudget.screens.RegisterScreen
import com.C2479785.beebudget.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.RegisterScreen.name ) {

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
    }

}