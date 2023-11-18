package com.C2479785.beebudget.screens.expense

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
        val movieList: List<Expense> = getExpenses()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            val selectedMonth = remember { mutableStateOf(0) }

            val selectedYear = remember { mutableStateOf(0) }


            Column(modifier = Modifier.padding(12.dp).fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(120.dp))
                    SelectInputField(
                        title = "Month",
                        options = viewModel.months,
                        currentSelection = selectedMonth,
                        onChange = {

                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    SelectInputField(
                        title = "Year",
                        options = viewModel.years,
                        currentSelection = selectedYear,
                        onChange = {

                        }
                    )
                }
                LazyColumn {
                    items(items = movieList){
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
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
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
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
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

fun getExpenses(): List<Expense> {
    return listOf(
        Expense(id = "tt0499549",
            amount = "54.60",
            description = "October Netflix Subscription",
            date = "15th October 2023",
            category = "Subscriptions"),

        Expense(id = "tt0416449",
            amount = "6.30",
            description = "2kg of  Chicken",
            date = "15th October 2023",
            category = "Food"),

        Expense(id = "tt0848228",
            amount = "15.55",
            description = "3 bars of chocolate",
            date = "16th October 2023",
            category = "Groceries"),

        Expense(id = "tt0993846",
            amount = "47.50",
            description = "Bus to uni in week 3",
            date = "16th October 2023",
            category = "Transportation"),

        Expense(id = "tt0816692",
            amount = "5.50",
            description = "Movie night in October",
            date = "17th October 2023",
            category = "Entertainment"),

        Expense(id = "tt0944947",
            amount = "5.50",
            description = "A random hangout",
            date = "18th October 2023",
            category = "PersonalCare"),


        Expense(id = "tt2306299",
            amount = "65.70",
            description = "Cab to office in October",
            date = "17th October 2023",
            category = "Others"),

        Expense(id = "tt0903747",
            amount = "9.59",
            description = "Checkout and checking",
            date = "18th October 2023",
            category = "PersonalCare"),

        Expense(id = "tt2707408",
            amount = "4.60",
            description = "5 bags of rice",
            date = "18th October 2023",
            category = "Transportation"),

        )
}