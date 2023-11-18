package com.C2479785.beebudget.screens.layout

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.ui.theme.PrimaryColor
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenLayout(
    navController : NavController,
    route : NavigationItem = NavigationItem.Dashboard,
    showFloatingActionButton: Boolean = false,
    floatingActionButtonAction: () -> Unit = {},
    screenContent: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = route.title)},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = PrimaryColor) ,
                actions = {
                           Text(
                               text = "Logout",
                               modifier = Modifier.clickable {
                                      FirebaseAuth.getInstance().signOut()
                                      navController.navigate(AppScreens.LoginScreen.name)
                               }
                           )
                           Icon(
                               imageVector = Icons.Default.ExitToApp,
                               contentDescription = "Logout",
                               modifier = Modifier.clickable {
                                    FirebaseAuth.getInstance().signOut()
                                    navController.navigate(AppScreens.LoginScreen.name)
                               }
                           )
                }
            )
        },
        floatingActionButton = {
             if(showFloatingActionButton) { FloatingActionButton(
                onClick = { floatingActionButtonAction() },
                containerColor = PrimaryColor,
                contentColor = Color.DarkGray
            ) {
                Icon(Icons.Filled.Add, "Add expense")
            }}
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomAppBar { BottomNavigationBar(navController = navController, route = route) }
        }
    ) {
        val that = it
        screenContent()
    }
}


@Composable
fun BottomNavigationBar(
    navController: NavController,
    route : NavigationItem = NavigationItem.Dashboard,
) {
    val items = listOf(
        NavigationItem.Budget,
        NavigationItem.Dashboard,
        NavigationItem.Expense,
    )
    var selectedItem = remember { mutableStateOf(0) }
    var currentRoute = remember { mutableStateOf(route.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute.value) {
            selectedItem.value = index
        }
    }

    NavigationBar(
        containerColor = PrimaryColor,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem.value == index,
                onClick = {
                          if (item.route  != currentRoute.value) {
                                 navController.navigate(item.route)
                          }
                }
            )
        }
    }
}