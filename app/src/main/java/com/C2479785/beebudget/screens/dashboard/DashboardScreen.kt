package com.C2479785.beebudget.screens.dashboard

import android.os.Build
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.C2479785.beebudget.components.DashboardExpenseSummaryCard
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.components.SpendingProgressBar
import com.C2479785.beebudget.models.DashboardSummaryDTO
import com.C2479785.beebudget.models.Expense
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.screens.budget.BudgetScreenViewModel
import com.C2479785.beebudget.screens.expense.ExpenseScreenViewModel
import com.C2479785.beebudget.screens.layout.MainScreenLayout
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    navController : NavController,
    viewModel: DashboardScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    budgetViewModel: BudgetScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    expenseViewModel: ExpenseScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    MainScreenLayout(
        navController,
        NavigationItem.Dashboard,
        showFloatingActionButton = true,
        floatingActionButtonAction = {
            navController.navigate(AppScreens.AddExpenseScreen.name)
        }
    ) {
        val context = LocalContext.current

        val selectedMonth = rememberSaveable { mutableIntStateOf(LocalDate.now().month.value -1) }

        val selectedYear = rememberSaveable { mutableIntStateOf(
            viewModel.years.indexOf(LocalDate.now().year.toString()))
        }

        val budget by budgetViewModel.currentBudget.observeAsState(initial = null)

        val expenses by expenseViewModel.expenses.observeAsState(initial = listOf<Expense>())

//        val dashboardSummary : MutableState<DashboardSummaryDTO?> = rememberSaveable{ mutableStateOf(null) }

        val dashboardSummary by viewModel.dashboardSummary.observeAsState(initial = null)

        val updateDashboard = fun(){
             viewModel.getDashboardSummary(budget!!, expenses);
        }

        val refreshData = fun () {
            budgetViewModel.geBudgetForForm(
                selectedMonth.value, selectedYear.value
            ){
                Log.d("Gbemileke", "Refreshing dashboard data Month : ${selectedMonth.value} , Year : ${selectedYear.value}")
                expenseViewModel.loadExpenses(
                    (selectedMonth.value + 1).toString(),
                    viewModel.years[selectedYear.value],
                    {}
                ){
                    updateDashboard();
                }
            }
        }

        refreshData()

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
                            Log.d("Gbemileke", "Month Changed to ${selectedMonth.value}")
                            refreshData()
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    SelectInputField(
                        title = "Year",
                        options = viewModel.years,
                        currentSelection = selectedYear,
                        onChange = {
                            Log.d("Gbemileke", "Year Changed to ${selectedYear.value}")
                            refreshData()
                        }
                    )
                }
                Row(
                    Modifier.height(98.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DashboardExpenseSummaryCard(
                        dashboardSummary?.totalBudget ?: 0.00f,
                        dashboardSummary?.totalExpense ?: 0.00f,
                        dashboardSummary?.spendRate ?: 0.00f
                    )
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
                    SpendingProgressBar(dashboardSummary?.subscriptions ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.food ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.groceries ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.transportation ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.entertainment ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.personalCare ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.others ?: 0.00f)
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
                    SpendingProgressBar(dashboardSummary?.spendRate ?: 0.00f)
                }
            }
        }
    }
}