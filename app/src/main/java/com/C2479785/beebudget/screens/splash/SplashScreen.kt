package com.C2479785.beebudget.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.C2479785.beebudget.R
import com.C2479785.beebudget.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )

        delay(2000L)

        if (FirebaseAuth.getInstance().currentUser?.uid.isNullOrEmpty()){
            navController.navigate(AppScreens.LoginScreen.name)
        }else {
            navController.navigate(AppScreens.DashboardScreen.name)
        }
    }

    Surface(modifier = Modifier
        .scale(scale.value),
        color = Color.White,
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.bee_budget_full_logo,
            ),
            contentDescription = "Bee Budget bee logo",
            contentScale = ContentScale.Fit
        )
    }

}