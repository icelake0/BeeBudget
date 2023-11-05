package com.C2479785.beebudget.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
        modifier: Modifier = Modifier,
        valueState: MutableState<String>,
        labelId: String,
        enabled: Boolean,
        isError: Boolean = false,
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