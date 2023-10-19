package com.mzaragozaserrano.presentationapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mzaragozaserrano.presentationapp.ui.utils.navigation.Navigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Navigation(modifier = Modifier.fillMaxSize(), navController = navController)
    }
}

@Preview
@Composable
private fun PresentationAppPrev() {
    MainScreen()
}