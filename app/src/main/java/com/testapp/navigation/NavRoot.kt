package com.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.presentation.UploadScreen
import com.testapp.presentation.resultsscreen.ResultsScreen
import com.testapp.presentation.mainscreen.MainScreen
import com.testapp.vm.TestViewModel


@Composable
fun NavRoot(vm: TestViewModel) {
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
            MainScreen(vm = vm,goToResultScreen = {
                navController.navigate(ResultScreenUI)
            })
        }
        composable<ResultScreenUI> {
            ResultsScreen(vm = vm)
        }
    }
}
