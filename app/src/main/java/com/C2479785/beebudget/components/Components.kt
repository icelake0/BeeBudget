package com.C2479785.beebudget.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.C2479785.beebudget.R
import com.C2479785.beebudget.models.Expense
import com.C2479785.beebudget.navigation.AppScreens
import com.C2479785.beebudget.ui.theme.PrimaryColor
import java.util.Calendar
import java.util.Date

@Composable
fun InputField(
        modifier: Modifier = Modifier,
        valueState: MutableState<String>,
        labelId: String,
        enabled: Boolean,
        isError: Boolean = false,
        leadingIcon: @Composable (() -> Unit)? = null,
        visualTransformation : VisualTransformation = VisualTransformation.None,
        isSingleLine: Boolean = true,
        keyboardType: KeyboardType = KeyboardType.Ascii,
        imeAction: ImeAction = ImeAction.Next,
        onAction: KeyboardActions = KeyboardActions.Default,
        onValueChange: (String) -> Unit = { valueState.value = it },
){
        OutlinedTextField(
                value = valueState.value,
                onValueChange = onValueChange,
                label = { Text(text =  labelId) },
                placeholder = { Text(text =  labelId) },
                leadingIcon = leadingIcon,
                singleLine = isSingleLine,
                enabled = enabled,
                isError = isError,
                visualTransformation = visualTransformation,
                textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = modifier
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = keyboardType
                ),
                keyboardActions = onAction,
        )
}

@Preview(showBackground = false)
@Composable
fun BeeBudgetBeeLogo() {
        Surface(modifier = Modifier
                .size(200.dp)
                .padding(5.dp),
                shape = CircleShape,
                border = BorderStroke(0.5.dp, Color.Transparent),
                shadowElevation = 4.dp,
        ) {
                Image(
                        painter = painterResource(
                                id = R.drawable.bee_budget_logo,
                        ),
                        contentDescription = "Bee Budget bee logo",
                        contentScale = ContentScale.Fit
                )
        }
}

@Preview(showBackground = false)
@Composable
fun BeeBudgetFullLogo() {
        Surface(modifier = Modifier
                .size(200.dp)
                .padding(5.dp)
        ) {
                Image(
                        painter = painterResource(
                                id = R.drawable.bee_budget_full_logo,
                        ),
                        contentDescription = "Bee Budget full logo",
                        contentScale = ContentScale.Fit
                )
        }
}

@Composable
fun DashboardExpenseSummaryCard(
        totalBudget: Float = 0.00f,
        totalExpense:Float  = 0.00f,
        spendRate:Float  = 0.00f,
        navController : NavController,
)
{
        Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(90.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
                Surface(
                        Modifier
                                .width(125.dp)
                                .fillMaxHeight()
                                .clickable(){
                                        navController.navigate(AppScreens.BudgetScreen.name)
                                },
                        shape = RoundedCornerShape(10.dp),
                        shadowElevation = 10.dp
                ){
                        Row(
                                Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.Top
                        ) {
                                Column(){
                                        Icon(
                                                modifier = Modifier
                                                        .width(50.dp)
                                                        .height(50.dp),
                                                imageVector = Icons.Default.Settings,
                                                contentDescription = "Budgeted amount"
                                        )
                                        Text(
                                                text = "Budget",
                                                style = MaterialTheme.typography.bodySmall
                                        )
                                        Text(
                                                text = "£${totalBudget}",
                                                maxLines = 1,
                                                style = MaterialTheme.typography.bodyLarge
                                        )
                                }
                        }
                }
                Surface(
                        Modifier.width(125.dp)
                                .fillMaxHeight()
                                .clickable(){
                                        navController.navigate(AppScreens.ExpenseScreen.name)
                                },
                        shape = RoundedCornerShape(10.dp),
                        shadowElevation = 10.dp
                ){
                        Row(
                                Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.Top
                        ) {
                                Column(){
                                        Icon(
                                                modifier = Modifier
                                                        .width(50.dp)
                                                        .height(50.dp),
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = "Budgeted amount"
                                        )
                                        Text(
                                                text = "Expense",
                                                style = MaterialTheme.typography.bodySmall
                                        )
                                        Text(
                                                text = "£${totalExpense}",
                                                maxLines = 1,
                                                style = MaterialTheme.typography.bodyLarge
                                        )
                                }
                        }
                }
                Surface(
                        Modifier.width(125.dp)
                                .fillMaxHeight()
                                .clickable(){
                                            //force reload dashboard
                                },
                        shape = RoundedCornerShape(10.dp),
                        shadowElevation = 10.dp
                ){
                        Row(
                                Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.Top
                        ) {
                                Column(){
                                        Icon(
                                                modifier = Modifier
                                                        .width(50.dp)
                                                        .height(50.dp),
                                                imageVector = Icons.Default.Refresh,
                                                contentDescription = "Budgeted amount"
                                        )
                                        Text(
                                                text = "Spend Rate",
                                                style = MaterialTheme.typography.bodySmall
                                        )
                                        Text(
                                                text = "${spendRate}%",
                                                style = MaterialTheme.typography.bodyLarge
                                        )
                                }
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun SpendingProgressBar(percentageProgress: Float = 50.00f) {

        var progressColor = Color(0xFF20AD26)

        if(percentageProgress  > 70){
                progressColor = Color(0xFFECBF39)
        }

        if(percentageProgress  > 80){
                progressColor = Color(0xFFEB8E06)
        }

        if(percentageProgress  >= 95){
                progressColor = Color(0xFFC90748)
        }


        Row {
                BoxWithConstraints(
                        Modifier
                                .fillMaxWidth()
                                .background(color = Color.Transparent)
                ) {
                        Column(
                                Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .background(
                                                Color.LightGray,
                                                shape = RoundedCornerShape(10.dp)
                                        )
                        ) {}
                        Column(
                                Modifier
                                        .background(progressColor, shape = RoundedCornerShape(10.dp))
                                        .width(percentageProgress / 100 * this.maxWidth)
                                        .height(30.dp)
                        ) {}
                        Column(
                                Modifier
                                        .background(
                                                Color.Transparent,
                                                shape = RoundedCornerShape(10.dp)
                                        )
                                        .width(this.maxWidth)
                                        .height(30.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                        ) {
                                Text(
                                        text = "${percentageProgress}%",
                                        style = MaterialTheme.typography.bodyLarge
                                )
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

//////////date picker component /////
@Composable
fun SelectDateField(context: Context, currentSelectedDate: MutableState<String>){
        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()
        if(currentSelectedDate.value.isEmpty()) {
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH)+1
                day = calendar.get(Calendar.DAY_OF_MONTH)
                currentSelectedDate.value = "$day/$month/$year"
        }else{
                year = currentSelectedDate.value.split('/')[2].toInt()
                month = currentSelectedDate.value.split('/')[1].toInt()
                day = currentSelectedDate.value.split('/')[0].toInt()
        }


        calendar.time = Date()

        val datePickerDialog = DatePickerDialog(
                context,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        currentSelectedDate.value = "$dayOfMonth/${month+1}/$year"
                }, year, month-1, day
        )

        OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.DarkGray
                ),
                onClick = {datePickerDialog.show()}
        ) {
                Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "drawable icons",
                        tint = PrimaryColor
                )
                Text(text = currentSelectedDate.value)
        }
}
//////////End date picker component /////



////////Select component /////

@Composable
fun SelectInputField(
        title: String, options : List<String>,
        currentSelection: MutableState<Int>,
        onChange: () -> Unit = {}
) {
        val showSelect = remember { mutableStateOf(false) }
        OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.DarkGray
                ),
                onClick = { showSelect.value = true }
        ) {
                Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "drawable icons",
                        tint = PrimaryColor
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(options[currentSelection.value])
                if (showSelect.value) {
                        AlertSingleChoicer(
                                state = showSelect,
                                selectedOption = currentSelection,
                                title = "Select $title",
                                options = options,
                                onChange = onChange
                        )
                }
        }
}

@Composable
fun AlertSingleChoicer(
        state: MutableState<Boolean>, title: String ,
        selectedOption: MutableState<Int>, options : List<String>,
        onChange: () -> Unit = {}
) {
        val selectedInputOption =  remember { mutableStateOf(selectedOption.value) }
        CommonDialog(
                title = title, state = state,
                selectedInputOption, selectedOption,
                onChange= onChange
        ) {
                SingleChoiceView(selectedInputOption, options)
        }
}


@Composable
fun CommonDialog(
        title: String?,
        state: MutableState<Boolean>,
        selectedInputOption: MutableState<Int>,
        selectedOption: MutableState<Int>,
        onChange: () -> Unit = {},
        content: @Composable (() -> Unit)? = null
) {
        val oldSelection = selectedInputOption.value
        AlertDialog(
                containerColor = Color.White,
                onDismissRequest = {
                        state.value = false
                },
                title = title?.let {
                        {
                                Column(
                                        Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                        Text(text = title)
                                        Divider(modifier = Modifier.padding(bottom = 8.dp))
                                }
                        }
                },
                text = content,
                dismissButton = {},
                confirmButton = {
                        Button(
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                                onClick = {
                                        selectedOption.value = selectedInputOption.value
                                        state.value = false
                                        onChange()
                                }
                        ) {
                                Text("Ok")
                        }
                }, modifier = Modifier.padding(vertical = 8.dp)
        )
}


@Composable
fun SingleChoiceView(
        selectedInputOption: MutableState<Int>,
        radioOptions : List<String>
) {
        val  selectedOption = remember { mutableStateOf(radioOptions[selectedInputOption.value]) }

        val onOptionSelected = fun(selectedText: String) {
                selectedOption.value = selectedText
                selectedInputOption.value = radioOptions.indexOf(selectedText)
        }
        Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
                radioOptions.forEach { text ->
                        Row(
                                Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                                selected = (text == selectedOption.value),
                                                onClick = {
                                                        onOptionSelected(text)
                                                }
                                        )
                                        .padding(vertical = 8.dp)
                        ) {
                                RadioButton(
                                        modifier = Modifier
                                                .size(3.dp)
                                                .padding(top = 5.dp),
                                        colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor),
                                        selected = (text == selectedOption.value),
                                        onClick = {
                                                onOptionSelected(text)
                                                selectedInputOption.value = radioOptions.indexOf(text)
                                        }
                                )
                                Text(
                                        text = text,
                                        modifier = Modifier.padding(start = 15.dp, top = 0.dp)
                                )
                        }
                }
        }
}

///// End of select component /////