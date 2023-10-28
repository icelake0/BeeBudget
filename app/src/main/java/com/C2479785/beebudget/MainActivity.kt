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
import com.C2479785.beebudget.pages.LoginPage
import com.C2479785.beebudget.pages.RegisterPage
import com.C2479785.beebudget.ui.theme.BeeBudgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeBudgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    BeeBudgetTheme {
        LoginPage()
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    BeeBudgetTheme {
        RegisterPage()
    }
}