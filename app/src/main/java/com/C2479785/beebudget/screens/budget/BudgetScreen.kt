package com.C2479785.beebudget.screens.budget

import android.graphics.drawable.shapes.Shape
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import com.C2479785.beebudget.screens.register.RegisterScreenViewModel
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun BudgetScreen(
    navController : NavController,
    viewModel: BudgetScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    MainScreenLayout(navController, NavigationItem.Budget) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {

            val selectedMonth = remember { mutableStateOf(0) }

            val selectedYear = remember { mutableStateOf(0) }

            val subscriptions = rememberSaveable { mutableStateOf("") }

            val food = rememberSaveable { mutableStateOf("") }

            val groceries = rememberSaveable { mutableStateOf("") }

            val transportation = rememberSaveable { mutableStateOf("") }

            val entertainment = rememberSaveable { mutableStateOf("") }

            val personalCare = rememberSaveable { mutableStateOf("") }

            val others = rememberSaveable { mutableStateOf("") }

            val loading by viewModel.loading.observeAsState(initial = false)

            val budgetFormIsvalid = remember(
                subscriptions.value, food.value,
                groceries.value, transportation.value,
                entertainment.value, personalCare.value,
                others.value
            ) {
                true
            }
            Surface( modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
            {
                Column(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                        Row( ) {
                            SelectInputField(
                                title = "Month",
                                options = viewModel.months,
                                currentSelection = selectedMonth
                            )
                            SelectInputField(
                                title = "Year",
                                options = viewModel.years,
                                currentSelection = selectedYear
                            )
                        }

                    InputField(
                        valueState = subscriptions, labelId = "Subscriptions",
                        leadingIcon = { Icon(Icons.Default.Refresh,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false
                    )

                    InputField(
                        valueState = food, labelId = "Food",
                        leadingIcon = { Icon(Icons.Default.Favorite,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        valueState = groceries, labelId = "Groceries",
                        leadingIcon = { Icon(Icons.Default.ShoppingCart,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        valueState = transportation, labelId = "Transportation",
                        leadingIcon = { Icon(Icons.Default.Send,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        valueState = entertainment, labelId = "Entertainment",
                        leadingIcon = { Icon(Icons.Default.Star,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )
                    InputField(
                        valueState = personalCare, labelId = "Personal Care",
                        leadingIcon = { Icon(Icons.Default.Face,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )
                    InputField(
                        valueState = others, labelId = "Others",
                        leadingIcon = { Icon(Icons.Default.Add,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                        enabled = !loading && budgetFormIsvalid,
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text(text = "Save Budget") },
                        onClick = {

                        },
                    )
                }

            }
        }
    }
}