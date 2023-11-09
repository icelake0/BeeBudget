package com.C2479785.beebudget.screens.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun RegisterScreen(
    navController : NavController,
    viewModel: RegisterScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current

    val firstName = rememberSaveable { mutableStateOf("") }
    val firstNameIsValid = remember(firstName.value) { firstName.value.trim().isNotEmpty() }

    val lastName = rememberSaveable { mutableStateOf("") }
    val lastNameIsValid= remember(lastName.value) { lastName.value.trim().isNotEmpty() }

    val email = rememberSaveable { mutableStateOf("") }
    val emailIsValid = remember(email.value) {android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches() }

    val password = rememberSaveable { mutableStateOf("") }
    val passwordIsValid = remember(password.value) { password.value.trim().isNotEmpty() }

    val confirmPassword = rememberSaveable { mutableStateOf("") }
    val confirmPasswordIsValid = remember(confirmPassword.value, password.value) {
        confirmPassword.value.trim().isNotEmpty() && password.value == confirmPassword.value
    }

    val loading by viewModel.loading.observeAsState(initial = false)

    val registerFormIsvalid = remember(
        firstName.value, lastName.value,
        password.value, confirmPassword.value,
        email.value
    ) {
        firstNameIsValid && lastNameIsValid && emailIsValid && passwordIsValid && confirmPasswordIsValid
    }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight())
    {
        Column(modifier = Modifier.padding(horizontal = 6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            BeeBudgetFullLogo()
            InputField(
                valueState = firstName, labelId = "First name",
                enabled = !loading,
                isError = !firstNameIsValid
            )

            InputField(
                valueState = lastName, labelId = "Last name",
                enabled = !loading,
                isError = !lastNameIsValid,
            )

            InputField(
                valueState = email, labelId = "Email",
                enabled = !loading,
                isError = !emailIsValid,
            )

            InputField(
                valueState = password, labelId = "Password",
                enabled = !loading,
                visualTransformation = PasswordVisualTransformation(),
                isError = !passwordIsValid,
            )

            InputField(
                valueState = confirmPassword, labelId = "Confirm password",
                enabled = !loading,
                visualTransformation = PasswordVisualTransformation(),
                isError = !confirmPasswordIsValid,
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                enabled = !loading && registerFormIsvalid,
                modifier = Modifier.fillMaxWidth(),
                content = { Text(text = "Register") },
                onClick = {
                    viewModel.createUser(
                        email.value, password.value,
                        firstName.value,  lastName.value,
                        {message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        },
                    ){
                        navController.navigate(AppScreens.DashboardScreen.name)
                    }
                },
            )
            Text(text = "Already have an account?")
            Text(
                text = "Login",
                color = PrimaryColor,
                modifier = Modifier
                    .clickable(enabled = !loading) {
                        navController.navigate(AppScreens.LoginScreen.name)
                    }
            )
        }

    }
}