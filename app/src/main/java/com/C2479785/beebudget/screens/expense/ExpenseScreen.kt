package com.C2479785.beebudget.screens.expense

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.models.Expense
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseScreen(
    navController : NavController,
    viewModel: ExpenseScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    MainScreenLayout(
        navController = navController,
        route = NavigationItem.Expense,
        showFloatingActionButton = true,
        floatingActionButtonAction = {
           navController.navigate(AppScreens.AddExpenseScreen.name)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            val loading by viewModel.loadingExpenses.observeAsState(initial = false)

            val expenses by viewModel.expenses.observeAsState(initial = listOf<Expense>())

            val selectedMonth = remember { mutableStateOf(LocalDate.now().month.value -1) }

            val selectedYear = remember { mutableStateOf(
                viewModel.years.indexOf(LocalDate.now().year.toString()))
            }

            val loadExpenses = fun(){
                viewModel.loadExpenses(
                    (selectedMonth.value + 1).toString(),
                    viewModel.years[selectedYear.value],
                    {}
                ){

                }
            }
            loadExpenses();

            Column(modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(100.dp))
                    SelectInputField(
                        title = "Month",
                        options = viewModel.months,
                        currentSelection = selectedMonth,
                        onChange = {
                            loadExpenses()
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    SelectInputField(
                        title = "Year",
                        options = viewModel.years,
                        currentSelection = selectedYear,
                        onChange = {
                            loadExpenses()
                        }
                    )
                }
                LazyColumn {
                    items(items = expenses){
                        ExpenseCard(expense = it)
                    }
                }

            }
        }
    }
}

@Composable
fun ExpenseCard(
    expense: Expense,
    onItemClick: (Expense) -> Unit = {}
) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(90.dp)
        .clickable {
            onItemClick(expense)
        },
        ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = Color.Transparent
            ) {
                Text(
                    text = "£${expense.amount}",
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Surface(
                color = Color.Transparent
            ) {
                Text(
                    text = "${expense.description}",
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = Color.Transparent
            ) {
                Text(
                    text = "${expense.date}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Surface(modifier = Modifier
                .padding(5.dp),
                color = Color(expense.pillColor()),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                shadowElevation = 5.dp
            ) {
                Text(
                    text = "${expense.category}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}