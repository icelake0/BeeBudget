package com.C2479785.beebudget.screens

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.C2479785.beebudget.navagation.BottomBar
import com.C2479785.beebudget.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
                    TopAppBar(
                        title = {Text(text = "Dashboard")},
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = PrimaryColor)
                    )
                 },
        bottomBar = { BottomBar(navController = navController) }
        ) {
        val that = it
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(
    navController: NavHostController
) {
    Text(text = "This is the dashboard")
    Text(text = "This is the dashboard")
    Text(text = "This is the dashboard")
    Text(text = "This is the dashboard")
    Text(text = "This is the dashboard")
    Text(text = "This is the dashboard")
}