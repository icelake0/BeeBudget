package com.C2479785.beebudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.C2479785.beebudget.navagation.AppNavigation
import com.C2479785.beebudget.screens.LoginScreen
import com.C2479785.beebudget.screens.MainScreen
import com.C2479785.beebudget.screens.RegisterScreen
import com.C2479785.beebudget.ui.theme.BeeBudgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                MainScreen()
            }
//            BeeBudgetTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    RegisterScreen()
//                }
//            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    BeeBudgetTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    App {
        MainScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    BeeBudgetTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    BeeBudgetTheme {
        RegisterScreen()
    }
}