package com.C2479785.beebudget.screens.budget

import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import com.C2479785.beebudget.screens.register.RegisterScreenViewModel
import com.C2479785.beebudget.ui.theme.PrimaryColor
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
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
            val context = LocalContext.current

//            val validFloatNumberPattern = remember { Regex("^\\d*\\.\\d+|\\d+\\.\\d*$") }
            val validWholeNumberPattern = remember { Regex("^\\d+\$") }

            val selectedMonth = remember { mutableStateOf(LocalDate.now().month.value -1) }

            val selectedYear = remember { mutableStateOf(
                viewModel.years.indexOf(LocalDate.now().year.toString()))
            }
            val subscriptions = rememberSaveable { mutableStateOf("") }
            val subscriptionsIsValid = remember(subscriptions.value) { subscriptions.value.trim().isNotEmpty() }

            val food = rememberSaveable { mutableStateOf("") }
            val foodIsValid = remember(food.value) { food.value.trim().isNotEmpty() }

            val groceries = rememberSaveable { mutableStateOf("") }
            val groceriesIsValid = remember(groceries.value) { groceries.value.trim().isNotEmpty() }

            val transportation = rememberSaveable { mutableStateOf("") }
            val transportationIsValid = remember(transportation.value) { transportation.value.trim().isNotEmpty() }

            val entertainment = rememberSaveable { mutableStateOf("") }
            val entertainmentIsValid = remember(entertainment.value) { entertainment.value.trim().isNotEmpty() }

            val personalCare = rememberSaveable { mutableStateOf("") }
            val personalCareIsValid = remember(personalCare.value) { personalCare.value.trim().isNotEmpty() }

            val others = rememberSaveable { mutableStateOf("") }
            val othersIsValid = remember(others.value) { others.value.trim().isNotEmpty() }

            val budgetFormIsValid = remember(
                subscriptions.value, food.value,
                groceries.value, transportation.value,
                entertainment.value, personalCare.value,
                others.value
            ) {
                subscriptionsIsValid
                        && foodIsValid
                        && groceriesIsValid
                        && transportationIsValid
                        && entertainmentIsValid
                        && personalCareIsValid
                        && othersIsValid
            }

            val loadedBudget by viewModel.loadedBudget.observeAsState(initial = false)

            val loading by viewModel.loading.observeAsState(initial = false)

            val currentBudget by viewModel.currentBudget.observeAsState(initial = null)

            val updateBudgetFormFields = fun () {
                viewModel.geBudgetForForm(
                    selectedMonth.value,
                    selectedYear.value,
                    errorCallback = {message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }){
                    //selectedMonth.value = currentBudget!!.month
                    //selectedYear.value = viewModel.years.indexOf((currentBudget!!.year).toString())
                    subscriptions.value = currentBudget!!.subscriptions.toString()
                    food.value = currentBudget!!.food.toString()
                    groceries.value = currentBudget!!.groceries.toString()
                    transportation.value =currentBudget!!.transportation.toString()
                    entertainment.value =currentBudget!!.entertainment.toString()
                    personalCare.value = currentBudget!!.personalCare.toString()
                    others.value = currentBudget!!.others.toString()
                }
            }

            if(!loadedBudget){
                updateBudgetFormFields()
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
                            Spacer(modifier = Modifier.width(110.dp))
                            SelectInputField(
                                title = "Month",
                                options = viewModel.months,
                                currentSelection = selectedMonth,
                                onChange = {
                                    viewModel.geBudgetForForm(
                                        selectedMonth = selectedMonth.value,
                                        selectedYear = selectedYear.value,
                                        errorCallback = {message ->
                                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                        }
                                    ) {
                                        updateBudgetFormFields()
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            SelectInputField(
                                title = "Year",
                                options = viewModel.years,
                                currentSelection = selectedYear,
                                onChange = {
                                    viewModel.geBudgetForForm(
                                        selectedMonth = selectedMonth.value,
                                        selectedYear = selectedYear.value,
                                        errorCallback = {message ->
                                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                        }
                                    ) {
                                        updateBudgetFormFields()
                                    }
                                }
                            )
                        }

                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = subscriptions, labelId = "Subscriptions",
                        leadingIcon = { Icon(Icons.Default.Refresh,  contentDescription = "", tint = PrimaryColor)},
                        onValueChange = {
                            if (it.isEmpty() || it.matches(validWholeNumberPattern)) {
                                //TODO Implement support for penny budgeting
                                //|| it.matches(validFloatNumberPattern)
                                subscriptions.value = it
                            }
                        },
                        enabled = !loading,
                        isError = false
                    )

                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = food, labelId = "Food",
                        leadingIcon = { Icon(Icons.Default.Favorite,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = groceries, labelId = "Groceries",
                        leadingIcon = { Icon(Icons.Default.ShoppingCart,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = transportation, labelId = "Transportation",
                        leadingIcon = { Icon(Icons.Default.Send,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = entertainment, labelId = "Entertainment",
                        leadingIcon = { Icon(Icons.Default.Star,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )
                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = personalCare, labelId = "Personal Care",
                        leadingIcon = { Icon(Icons.Default.Face,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )
                    InputField(
                        keyboardType = KeyboardType.Number,
                        valueState = others, labelId = "Others",
                        leadingIcon = { Icon(Icons.Default.Add,  contentDescription = "", tint = PrimaryColor)},
                        enabled = !loading,
                        isError = false,
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                        enabled = !loading && budgetFormIsValid,
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text(text = "Update Budget") },
                        onClick = {
                            viewModel.updateBudget(
                                month = selectedMonth.value,
                                year = selectedYear.value,
                                subscriptions = subscriptions.value.toInt(),
                                food = food.value.toInt(),
                                groceries = groceries.value.toInt(),
                                transportation = transportation.value.toInt(),
                                entertainment = entertainment.value.toInt(),
                                personalCare = personalCare.value.toInt(),
                                others = others.value.toInt(),
                                budgetId = currentBudget?.id ?: null,
                                {message ->
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                },
                            ){
                                Toast.makeText(context,
                                    "Budget Updated successfully",
                                    Toast.LENGTH_SHORT).show()
                            }
                        },
                    )
                }

            }
        }
    }
}