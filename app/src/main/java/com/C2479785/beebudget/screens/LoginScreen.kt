package com.C2479785.beebudget.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController : NavController) {
    val email = rememberSaveable { mutableStateOf("") }

    val password = rememberSaveable { mutableStateOf("") }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight())
    {
        Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            BeeBudgetFullLogo()
            InputField(
                valueState = email, labelId = "email",
                enabled = true,
            )

            InputField(
                valueState = password, labelId = "password",
                enabled = true,
                visualTransformation = PasswordVisualTransformation(),
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                content = { Text(text = "Login") },
            )
            Text(text = "Don't have an account yet?")
            Text(
                text = "Register",
                color = PrimaryColor,
                modifier = Modifier
                    .clickable {
                        navController.navigate(AppScreens.RegisterScreen.name)
                    }
            )
        }

    }
}