package com.C2479785.beebudget.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.DashboardExpenseSummaryCard
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.components.SpendingProgressBar
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    navController : NavController,
    viewModel: DashboardScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    MainScreenLayout(
        navController,
        NavigationItem.Dashboard,
        showFloatingActionButton = true,
        floatingActionButtonAction = {
            navController.navigate(AppScreens.AddExpenseScreen.name)
        }
    ) {

        val selectedMonth = remember { mutableStateOf(LocalDate.now().month.value -1) }

        val selectedYear = remember { mutableStateOf(
            viewModel.years.indexOf(LocalDate.now().year.toString()))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 65.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
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
//                            loadExpenses()
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    SelectInputField(
                        title = "Year",
                        options = viewModel.years,
                        currentSelection = selectedYear,
                        onChange = {
//                            loadExpenses()
                        }
                    )
                }
                Row(
                    Modifier.height(98.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DashboardExpenseSummaryCard()
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Subscriptions",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(45.66f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Food",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(87.29f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Groceries",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(0.00f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Transportation",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(10.58f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Entertainment",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(70.45f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Personal Care",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(50.78f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Others",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(98.48f)
                }

                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = "Total",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpendingProgressBar(77.48f)
                }
            }
        }
    }
}