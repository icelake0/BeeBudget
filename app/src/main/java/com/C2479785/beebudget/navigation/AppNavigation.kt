package com.C2479785.beebudget.navagation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.DashboardScreen
import com.C2479785.beebudget.screens.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.name ) {
//
//        composable(AppScreens.DashboardScreen.name){
//            DashboardScreen(navController = navController)
//        }
    }

}