package com.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.presentation.UploadScreen
import com.testapp.presentation.mainscreen.MainScreen
import com.testapp.vm.TestViewModel


@Composable
fun NavRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UploadScreenUi
    ) {
        composable<UploadScreenUi> {
            UploadScreen(goToMainScreen = {
                navController.navigate(MainScreenUi)
            })
        }
        composable<MainScreenUi> {
            MainScreen()
        }
    }
}
