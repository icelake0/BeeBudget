package com.C2479785.beebudget.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DashboardScreen(navController : NavController) {
    MainScreenLayout(navController, NavigationItem.Dashboard) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Dashboard page"
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate(AppScreens.LoginScreen.name)
            }) {
                Text(
                    text = "Logout"
                )
            }
        }
    }
}