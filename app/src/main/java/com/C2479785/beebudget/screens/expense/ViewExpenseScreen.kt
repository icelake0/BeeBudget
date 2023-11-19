package com.C2479785.beebudget.screens.expense

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.R
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.ExpenseCard
import com.C2479785.beebudget.components.SelectDateField
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.models.Expense
import com.C2479785.beebudget.models.ExpenseCategories
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.screens.layout.DetailsScreenLayout
import com.C2479785.beebudget.ui.theme.PrimaryColor

@Composable
fun ViewExpenseScreen(
    navController : NavController,
    viewModel: ExpenseScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    expenseId: String?
) {

    DetailsScreenLayout(
        navController = navController,
        route = NavigationItem.Expense
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 60.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val context = LocalContext.current

            val loading by viewModel.findingExpenseById.observeAsState(initial = false)

            val expense by viewModel.expenseFoundById.observeAsState(initial = null)

            if(expense == null){
                viewModel.findExpenseById(expenseId!!, {message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                })
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(modifier = Modifier.padding(12.dp).size(240.dp)) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.bee_budget_logo,
                        ),
                        contentScale = ContentScale.Fit,
                        contentDescription = expense?.description
                    )

                }
                ExpenseCard(expense ?: getNullExpense())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment =Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        content = { Text(text = "Delete") },
                        enabled = !loading,
                        onClick = {

                        },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                        content = { Text(text = "Update") },
                        enabled = !loading,
                        onClick = {

                        },
                    )
                }
            }
        }
    }
}

fun getNullExpense() : Expense {
    return Expense.nullInstance()
}