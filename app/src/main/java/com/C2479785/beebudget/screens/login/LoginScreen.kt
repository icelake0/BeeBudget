package com.C2479785.beebudget.screens.login

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.register.LoginScreenViewModel
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun LoginScreen(
    navController : NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val context = LocalContext.current

    val email = rememberSaveable { mutableStateOf("") }

    val password = rememberSaveable { mutableStateOf("") }

    val loginFormIsValid = remember(email.value, password.value) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                && password.value.trim().isNotEmpty()
    }

    val loading by viewModel.loading.observeAsState(initial = false)

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight())
    {
        Column(modifier = Modifier.padding(horizontal = 6.dp),
            verticalArrangement = Arrangement.Center,
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
                modifier = Modifier.fillMaxWidth(),
                content = { Text(text = "Login") },
                enabled = loginFormIsValid && !loading,
                onClick = {
                    viewModel.loginUser(email.value, password.value, {
                        Toast.makeText(context, "Credentials provided is invalid", Toast.LENGTH_SHORT).show()
                    }) {
                        navController.navigate(AppScreens.DashboardScreen.name)
                    }
                },
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