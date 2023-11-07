package com.C2479785.beebudget.screens.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.screens.layout.MainScreenLayout

@Composable
fun ExpenseScreen(navController : NavController) {
    MainScreenLayout(navController, NavigationItem.Expense) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Expense page"
            )
        }
    }
}