package com.C2479785.beebudget.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.C2479785.beebudget.R
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
                dismissButton = {
                        Button(
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                                onClick = { state.value = false }
                        ) {
                                Text("Cancel")
                        }
                },
                confirmButton = {
                        Button(
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                                onClick = {
                                        selectedOption.value = selectedInputOption.value
                                        onChange()
                                        state.value = false
                                }
                        ) {
                                Text("Ok")
                        }
                }, modifier = Modifier.padding(vertical = 8.dp)
        )
}


@Composable
fun SingleChoiceView(selectedInputOption: MutableState<Int>, radioOptions : List<String>) {
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[selectedInputOption.value]) }
        Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
                radioOptions.forEach { text ->
                        Row(
                                Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                                selected = (text == selectedOption),
                                                onClick = {
                                                        onOptionSelected(text)
                                                }
                                        )
                                        .padding(vertical = 8.dp)
                        ) {
                                RadioButton(
                                        modifier = Modifier.size(3.dp).padding(top = 5.dp),
                                        colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor),
                                        selected = (text == selectedOption),
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