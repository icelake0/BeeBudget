package com.C2479785.beebudget.screens.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.SelectDateField
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.models.ExpenseCategories
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.screens.layout.DetailsScreenLayout
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun AddExpenseScreen(
    navController : NavController,
    viewModel: ExpenseScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    DetailsScreenLayout(
        navController = navController,
        route = NavigationItem.Expense
    ) {
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            val loading = false

            val selectedDate = remember { mutableStateOf("") }
            val selectedCategory = remember { mutableStateOf(0) }

            val description = rememberSaveable { mutableStateOf("") }
            val descriptionIsValid = remember(description.value) { description.value.trim().isNotEmpty() }

            val amount = rememberSaveable { mutableStateOf("") }
            val amountIsValid = remember(amount.value) { amount.value.trim().isNotEmpty() }



            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                BeeBudgetFullLogo()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    SelectDateField(context, selectedDate)
                    Spacer(modifier = Modifier.width(5.dp))
                    SelectInputField(
                        title = "Category",
                        options = ExpenseCategories.getList(),
                        currentSelection = selectedCategory,
                        onChange = {

                        }
                    )
                }
                InputField(
                    keyboardType = KeyboardType.Ascii,
                    valueState = description, labelId = "Description",
                    leadingIcon = { Icon(Icons.Default.ShoppingCart,  contentDescription = "", tint = PrimaryColor)},
                    onValueChange = {

                    },
                    enabled = !loading,
                    isError = false
                )

                InputField(
                    keyboardType = KeyboardType.Number,
                    valueState = amount, labelId = "Amount",
                    leadingIcon = { Icon(Icons.Default.ShoppingCart,  contentDescription = "", tint = PrimaryColor)},
                    onValueChange = {

                    },
                    enabled = !loading,
                    isError = false
                )
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                    modifier = Modifier.fillMaxWidth(),
                    content = { Text(text = "Save Expense") },
                    enabled = !loading,
                    onClick = {

                    },
                )
            }
        }
    }
}