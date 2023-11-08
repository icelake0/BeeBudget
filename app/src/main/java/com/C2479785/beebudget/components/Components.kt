package com.C2479785.beebudget.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
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
        onAction: KeyboardActions = KeyboardActions.Default
){
        OutlinedTextField(
                value = valueState.value,
                onValueChange = { valueState.value = it },
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
                keyboardActions = onAction
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




////////Select component /////

@Composable
fun SelectInputField(title: String, options : List<String>, currentSelection: MutableState<Int>) {
        val showSelect = remember { mutableStateOf(false) }
        OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = PrimaryColor
                ),
                onClick = { showSelect.value = true }
        ) {
                Text(options[currentSelection.value])
                if (showSelect.value) {
                        AlertSingleChoicer(
                                state = showSelect,
                                selectedOption = currentSelection,
                                title = "Select $title",
                                options = options
                        )
                }
        }
}

@Composable
fun AlertSingleChoicer(state: MutableState<Boolean>, title: String , selectedOption: MutableState<Int>, options : List<String>) {
        val selectedInputOption =  remember { mutableStateOf(selectedOption.value) }
        CommonDialog(title = title, state = state, selectedInputOption, selectedOption) {
                SingleChoiceView(selectedInputOption, options)
        }
}


@Composable
fun CommonDialog(
        title: String?,
        state: MutableState<Boolean>,
        selectedInputOption: MutableState<Int>,
        selectedOption: MutableState<Int>,
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