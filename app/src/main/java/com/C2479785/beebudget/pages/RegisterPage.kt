package com.C2479785.beebudget.pages

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
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun RegisterPage() {
    val firstName = rememberSaveable { mutableStateOf("") }

    val lastName = rememberSaveable { mutableStateOf("") }

    val email = rememberSaveable { mutableStateOf("") }

    val password = rememberSaveable { mutableStateOf("") }

    val confirmPassword = rememberSaveable { mutableStateOf("") }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight())
    {
        Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            BeeBudgetFullLogo()
            InputField(
                valueState = firstName, labelId = "first name",
                enabled = true,
                onAction = KeyboardActions {
                },
            )

            InputField(
                valueState = lastName, labelId = "last name",
                enabled = true,
                onAction = KeyboardActions {
                },
            )

            InputField(
                valueState = email, labelId = "email",
                enabled = true,
                onAction = KeyboardActions {
                },
            )

            InputField(
                valueState = password, labelId = "password",
                enabled = true,
                visualTransformation = PasswordVisualTransformation(),
                onAction = KeyboardActions {
                },
            )

            InputField(
                valueState = confirmPassword, labelId = "confirm password",
                enabled = true,
                visualTransformation = PasswordVisualTransformation(),
                onAction = KeyboardActions {
                },
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                content = { Text(text = "Register") },
            )
            Text(text = "Already have an account?")
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                onClick = { /*TODO*/ },
                content = { Text(text = "Login") }
            )
        }

    }
}