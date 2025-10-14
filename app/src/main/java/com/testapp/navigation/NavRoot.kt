package com.testapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.presentation.UploadScreen
import com.testapp.presentation.mainscreen.MainScreen
import com.testapp.vm.TestViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


@Composable
fun NavRoot(
    vm: TestViewModel = viewModel()
) {
    val navController = rememberNavController()
    val quiz by vm.quiz.collectAsState()
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

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
