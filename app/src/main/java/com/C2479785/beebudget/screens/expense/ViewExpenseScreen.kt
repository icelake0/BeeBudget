package com.C2479785.beebudget.screens.expense

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableState
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.C2479785.beebudget.R
import com.C2479785.beebudget.components.BeeBudgetFullLogo
import com.C2479785.beebudget.components.CameraView
import com.C2479785.beebudget.components.ExpenseCard
import com.C2479785.beebudget.components.SelectDateField
import com.C2479785.beebudget.components.InputField
import com.C2479785.beebudget.components.SelectInputField
import com.C2479785.beebudget.models.Expense
import com.C2479785.beebudget.models.ExpenseCategories
import com.C2479785.beebudget.navagation.NavigationItem
import com.C2479785.beebudget.screens.layout.DetailsScreenLayout
import com.C2479785.beebudget.ui.theme.PrimaryColor
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

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
                .padding(top = 90.dp, bottom = 60.dp, start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val context = LocalContext.current

            val activity = LocalContext.current as Activity

            val loading by viewModel.findingExpenseById.observeAsState(initial = false)

            val expense by viewModel.expenseFoundById.observeAsState(initial = null)

            val expensePhotoURI by viewModel.expensePhotoURI.observeAsState(initial = null)

            if(expense == null){
                viewModel.findExpenseById(expenseId!!, {message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                })
            }

            var shouldShowCamera: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
            lateinit var outputDirectory: File
            lateinit var cameraExecutor: ExecutorService

            val getOutputDirectory = fun (): File {
                val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                    File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
                }

                return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
            }
            outputDirectory = getOutputDirectory()
            cameraExecutor = Executors.newSingleThreadExecutor()


            val requestCameraPermission = fun() {
                when {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        shouldShowCamera.value = true
                    }

                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.CAMERA
                    ) -> Toast.makeText(context, "You need to grant permission to yse camera", Toast.LENGTH_SHORT).show()

                    else -> Toast.makeText(context, "You need to grant permission to yse camera", Toast.LENGTH_SHORT).show()
                }
            }

            var handleImageCapture = fun (uri: Uri) {
                shouldShowCamera.value = false
                viewModel.uploadPhotoToFireStore(uri, expenseId!!, {message->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }){
                    viewModel.downloadPhotoFromFireStore(expense!!)
                }
                cameraExecutor.shutdown()
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(modifier = Modifier.padding(12.dp).size(240.dp).clickable {
                    requestCameraPermission()
                }) {
                    if (shouldShowCamera.value) {
                        CameraView(
                            outputDirectory = outputDirectory,
                            executor = cameraExecutor,
                            onImageCaptured = {handleImageCapture(it)},
                            onError = { }
                        )
                    }

                    if (expensePhotoURI !== null) {
                        Image(
                            painter = rememberImagePainter(expensePhotoURI),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                ExpenseCard(expense ?: getNullExpense())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment =Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        content = { Text(text = "Delete") },
                        enabled = !loading,
                        onClick = {
                            viewModel.deleteExpenseById(expenseId!!, {message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }){ navController.popBackStack()}
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